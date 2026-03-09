package com.sep.classManagement.adapter.out.persistance;

import com.sep.classManagement.domain.model.Classroom;
import org.springframework.stereotype.Component;

@Component
public class ClassroomMapper {

    public ClassroomJpaEntity toJpaEntity(Classroom domain) {
        if (domain == null) return null;
        return ClassroomJpaEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .code(domain.getCode())
                .description(domain.getDescription())
                .teacherId(domain.getTeacherId())
                .status(domain.getStatus())
                .createdAt(domain.getCreatedAt())
                .build();
    }

    public Classroom toDomain(ClassroomJpaEntity entity) {
        if (entity == null) return null;
        return Classroom.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .description(entity.getDescription())
                .teacherId(entity.getTeacherId())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}