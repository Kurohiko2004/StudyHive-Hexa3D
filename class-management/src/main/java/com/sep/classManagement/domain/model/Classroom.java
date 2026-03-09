package com.sep.classManagement.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Lớp Domain Entity: Lớp học
 * Trái tim của hệ thống. Chỉ chứa logic nghiệp vụ thuần túy, KHÔNG chứa các annotation của Spring hay Database.
 */
@Getter
@Builder
public class Classroom {
    private String id;
    private String name;
    private String code;
    private String description;
    private String teacherId;
    private String status;
    private LocalDateTime createdAt;

    // Factory method: Khởi tạo Lớp học mới với các quy tắc mặc định
    public static Classroom createNew(String name, String description, String teacherId) {
        return Classroom.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .code(generateClassCode())
                .description(description)
                .teacherId(teacherId)
                .status("ACTIVE")
                .createdAt(LocalDateTime.now())
                .build();
    }

    // Logic sinh mã tự động cho học sinh join
    private static String generateClassCode() {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    // Business rule: Kiểm tra tính hợp lệ trước khi lưu
    public void validate() {
        if (this.name == null || this.name.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên lớp học không được để trống");
        }
        if (this.teacherId == null || this.teacherId.trim().isEmpty()) {
            throw new IllegalArgumentException("Phải có giáo viên quản lý lớp");
        }
    }
}