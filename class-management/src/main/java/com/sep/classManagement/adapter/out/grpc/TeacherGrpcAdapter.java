package com.sep.classManagement.adapter.out.grpc;

import com.sep.classManagement.application.port.out.CheckUserPort;
import com.sep.commonModule.exception.BusinessValidationException;
import com.sep.commonModule.grpc.user.v1.GetUserRequest;
import com.sep.commonModule.grpc.user.v1.GetUserResponse;
import com.sep.commonModule.grpc.user.v1.UserSummary;
import com.sep.commonModule.grpc.user.v1.UserServiceGrpc;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;


@Component
public class TeacherGrpcAdapter implements CheckUserPort {

    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userServiceStub;

    @Override
    public CheckUserPort.TeacherInfo getTeacherInfo(String teacherId) {
        try {
            // 1. Prepare generic User Request
            GetUserRequest request = GetUserRequest.newBuilder()
                    .setUserId(teacherId)
                    .build();

            // 2. Call gRPC to get User Profile
            GetUserResponse response = userServiceStub.getUserProfile(request);
            UserSummary user = response.getUser();

            // 3. Validate Role locally (Adapter Logic)
            if (!"TEACHER".equalsIgnoreCase(user.getRole())) {
                throw new BusinessValidationException("teacherId",
                    "User with id " + teacherId + " is not a TEACHER. Current role: " + user.getRole());
            }

            // 4. Map to domain object
            return new TeacherInfo(
                    user.getId(),
                    user.getRole(),
                    user.getStatus()
            );

        } catch (StatusRuntimeException e) {
            switch (e.getStatus().getCode()) {
                case NOT_FOUND:
                    throw new BusinessValidationException("teacherId", "Teacher (User) not found");
                case UNAVAILABLE:
                    throw new BusinessValidationException("teacherId", "User service is unavailable");
                default:
                    throw new BusinessValidationException("teacherId", "Error checking teacher info: " + e.getStatus().getDescription());
            }
        }
    }
}

