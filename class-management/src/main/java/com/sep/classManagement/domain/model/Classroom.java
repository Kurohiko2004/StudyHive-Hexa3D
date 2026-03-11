package com.sep.classManagement.domain.model;

import com.sep.commonModule.domain.model.EntityStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class Classroom {
    private String id;
    private String name;
    private String code;
    private String description;

    private String subjectId;
    private String levelId;
    private List<String> schedule;

    private String teacherId;
    private EntityStatus status;
    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String updatedBy;

    public static Classroom createNew(String name, String description, String subjectId, String levelId, String teacherId, List<String> schedule) {
        Classroom classroom = Classroom.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .code(generateClassCode())
                .description(description)
                .subjectId(subjectId)
                .levelId(levelId)
                .teacherId(teacherId)
                .schedule(schedule)
                .status(EntityStatus.ACTIVE)
                .createdAt(Instant.now())
                .build();

        classroom.validate();
        return classroom;
    }

    private static String generateClassCode() {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    public void validate() {
        // Name: Bắt buộc, 1-50 ký tự
        if (this.name == null || this.name.trim().isEmpty()) {
            throw new IllegalArgumentException("Class name cannot be blank");
        }
        if (this.name.length() > 50) {
            throw new IllegalArgumentException("Class name must be between 1 and 50 characters");
        }

        // Subject: Bắt buộc
        if (this.subjectId == null || this.subjectId.trim().isEmpty()) {
            throw new IllegalArgumentException("Subject is required");
        }

        // Level: Bắt buộc
        if (this.levelId == null || this.levelId.trim().isEmpty()) {
            throw new IllegalArgumentException("Level is required");
        }

        // Description: Tối đa 1000 ký tự
        if (this.description != null && this.description.length() > 1000) {
            throw new IllegalArgumentException("Description cannot exceed 1000 characters");
        }
    }
}