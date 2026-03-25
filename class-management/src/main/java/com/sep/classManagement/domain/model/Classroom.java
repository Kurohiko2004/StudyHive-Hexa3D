package com.sep.classManagement.domain.model;

import com.sep.commonModule.domain.model.EntityStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

        if (this.name == null || this.name.trim().isEmpty()) {
            throw new IllegalArgumentException("Class name cannot be blank");
        }
        if (this.name.length() > 50) {
            throw new IllegalArgumentException("Class name must be between 1 and 50 characters");
        }

        if (this.subjectId == null || this.subjectId.trim().isEmpty()) {
            throw new IllegalArgumentException("Subject is required");
        }

        if (this.levelId == null || this.levelId.trim().isEmpty()) {
            throw new IllegalArgumentException("Level is required");
        }

        if (this.description != null && this.description.length() > 1000) {
            throw new IllegalArgumentException("Description cannot exceed 1000 characters");
        }
    }

    public void update(String name, String description, String subjectId, String levelId, String teacherId, List<String> schedule, EntityStatus status) {
        if (name != null) this.name = name;
        if (description != null) this.description = description; 
        if (subjectId != null) this.subjectId = subjectId;
        if (levelId != null) this.levelId = levelId;
        if (teacherId != null) this.teacherId = teacherId;
        if (schedule != null) this.schedule = schedule;
        if (status != null) this.status = status;
        this.updatedAt = Instant.now();
        validate();
    }
}