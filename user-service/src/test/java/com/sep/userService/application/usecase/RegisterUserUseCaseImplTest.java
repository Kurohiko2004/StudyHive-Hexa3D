package com.sep.userService.application.usecase;

import com.sep.commonModule.domain.model.EntityStatus;
import com.sep.commonModule.exception.BusinessValidationException;
import com.sep.userService.adapter.in.web.dto.UserResponse;
import com.sep.userService.application.command.RegisterUserCommand;
import com.sep.userService.application.port.out.PasswordEncoderRepository;
import com.sep.userService.application.port.out.UserRepository;
import com.sep.userService.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Unit test for RegisterUserUseCaseImpl.
 *
 * Không load Spring context (không có @SpringBootTest).
 * Chỉ mock 2 port out: UserRepository và PasswordEncoderRepository.
 * → Test nhanh, isolated, đúng tinh thần Hexagonal Architecture.
 */
@ExtendWith(MockitoExtension.class)
class RegisterUserUseCaseImplTest {

        // === Mock các Port Out ===
        @Mock
        private UserRepository userRepository;

        @Mock
        private PasswordEncoderRepository passwordEncoderRepository;

        // === SUT (System Under Test) ===
        private RegisterUserUseCaseImpl useCase;

        @BeforeEach
        void setUp() {
                // Khởi tạo thủ công, không cần @Autowired hay Spring
                useCase = new RegisterUserUseCaseImpl(userRepository, passwordEncoderRepository);
        }

        // =========================================================================
        // Happy Path
        // =========================================================================

        @Test
        @DisplayName("Đăng ký thành công với thông tin hợp lệ")
        void registerUser_withValidInput_shouldReturnUserResponse() {
                // --- Arrange ---
                RegisterUserCommand command = new RegisterUserCommand(
                                "test@example.com",
                                "Password1", // hợp lệ: >= 8 ký tự, có chữ và số
                                "Nguyen Van A",
                                "STUDENT");

                // Email chưa tồn tại
                when(userRepository.existsByEmail("test@example.com")).thenReturn(false);

                // Giả lập encode trả về chuỗi hash
                when(passwordEncoderRepository.encode(anyString())).thenReturn("$2a$hashed_password");

                // Giả lập save trả về UserResponse
                UserResponse expectedResponse = UserResponse.builder()
                                .id("some-uuid")
                                .email("test@example.com")
                                .fullName("Nguyen Van A")
                                .role("STUDENT")
                                .status(EntityStatus.ACTIVE)
                                .build();
                when(userRepository.save(any(User.class))).thenReturn(expectedResponse);

                // --- Act ---
                UserResponse result = useCase.execute(command);

                // --- Assert ---
                assertThat(result).isNotNull();
                assertThat(result.getEmail()).isEqualTo("test@example.com");
                assertThat(result.getFullName()).isEqualTo("Nguyen Van A");
                assertThat(result.getRole()).isEqualTo("STUDENT");
                assertThat(result.getStatus()).isEqualTo(EntityStatus.ACTIVE);

                // Xác nhận các port đã được gọi đúng
                verify(userRepository).existsByEmail("test@example.com");
                verify(passwordEncoderRepository).encode("Password1");
                verify(userRepository).save(any(User.class));
        }

        @Test
        @DisplayName("Đăng ký không có role thì mặc định là STUDENT")
        void registerUser_withoutRole_shouldDefaultToStudent() {
                // --- Arrange ---
                RegisterUserCommand command = new RegisterUserCommand(
                                "noRole@example.com",
                                "Password1",
                                "Tran Thi B",
                                "" // role rỗng → fallback STUDENT
                );

                when(userRepository.existsByEmail(anyString())).thenReturn(false);
                when(passwordEncoderRepository.encode(anyString())).thenReturn("$2a$hashed");

                UserResponse expectedResponse = UserResponse.builder()
                                .id("uuid-2")
                                .email("noRole@example.com")
                                .fullName("Tran Thi B")
                                .role("STUDENT")
                                .status(EntityStatus.ACTIVE)
                                .build();
                when(userRepository.save(any(User.class))).thenReturn(expectedResponse);

                // --- Act ---
                UserResponse result = useCase.execute(command);

                // --- Assert ---
                assertThat(result.getRole()).isEqualTo("STUDENT");
        }

        // =========================================================================
        // Sad Paths (lỗi nghiệp vụ)
        // =========================================================================

        @Test
        @DisplayName("Ném BusinessValidationException khi email đã tồn tại")
        void registerUser_withDuplicateEmail_shouldThrowBusinessValidationException() {
                // --- Arrange ---
                RegisterUserCommand command = new RegisterUserCommand(
                                "existing@example.com",
                                "Password1",
                                "Le Van C",
                                "TEACHER");

                // Email đã tồn tại trong DB
                when(userRepository.existsByEmail("existing@example.com")).thenReturn(true);

                // --- Act & Assert ---
                assertThatThrownBy(() -> useCase.execute(command))
                                .isInstanceOf(BusinessValidationException.class)
                                .hasMessageContaining("existing@example.com");

                // Đảm bảo không gọi save khi email đã tồn tại
                verify(userRepository, never()).save(any());
        }

        @Test
        @DisplayName("Ném exception khi password không đủ mạnh (thiếu số)")
        void registerUser_withWeakPassword_shouldThrowException() {
                // --- Arrange ---
                RegisterUserCommand command = new RegisterUserCommand(
                                "test@example.com",
                                "onlyletters", // không có số → Password.createNew() ném exception ngay
                                "Pham Thi D",
                                "STUDENT");
                // Không cần stub gì — Password.createNew() throw trước khi chạm đến port nào

                // --- Act & Assert ---
                assertThatThrownBy(() -> useCase.execute(command))
                                .isInstanceOf(IllegalArgumentException.class);

                // Save không được gọi
                verify(userRepository, never()).save(any());
        }

        @Test
        @DisplayName("Ném exception khi role không hợp lệ (chỉ STUDENT/TEACHER được phép)")
        void registerUser_withInvalidRole_shouldThrowException() {
                // --- Arrange ---
                RegisterUserCommand command = new RegisterUserCommand(
                                "test@example.com",
                                "Password1",
                                "Nguyen Van E",
                                "ADMIN" // ADMIN không được phép tự đăng ký
                );

                when(userRepository.existsByEmail(anyString())).thenReturn(false);
                when(passwordEncoderRepository.encode(anyString())).thenReturn("$2a$hashed");

                // --- Act & Assert ---
                assertThatThrownBy(() -> useCase.execute(command))
                                .isInstanceOf(IllegalArgumentException.class)
                                .hasMessageContaining("STUDENT or TEACHER");

                verify(userRepository, never()).save(any());
        }
}
