package com.sep.classManagement.config.security;

import com.sep.classManagement.application.port.out.LoadClassroomPort;
import com.sep.classManagement.domain.model.Classroom;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("classroomSecurity")
@RequiredArgsConstructor
public class ClassroomSecurity {

    private final LoadClassroomPort loadClassroomPort;

    /**
     * Kiểm tra xem người dùng hiện tại có phải là giáo viên sở hữu lớp học này không.
     * @param classroomId ID của lớp học cần kiểm tra
     * @return true nếu là chủ sở hữu, ngược lại false
     */
    public boolean isOwner(String classroomId) {
        String currentUserId = getCurrentUserId();

        Optional<Classroom> classroom = loadClassroomPort.loadClassroomById(classroomId);

        // Kiểm tra nếu lớp học tồn tại và teacherId trùng với ID người dùng hiện tại
        return classroom.map(c -> c.getTeacherId().equals(currentUserId))
                .orElse(false);
    }

    private String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        // Lấy userId từ Principal (đã được BaseJwtAuthFilter thiết lập)
        return (String) authentication.getPrincipal();
    }
}
