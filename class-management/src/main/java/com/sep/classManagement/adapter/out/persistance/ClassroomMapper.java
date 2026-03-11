package com.sep.classManagement.adapter.out.persistance;

import com.sep.classManagement.domain.model.Classroom;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClassroomMapper {

    ClassroomJpaEntity toJpaEntity(Classroom domain);

    Classroom toDomain(ClassroomJpaEntity entity);
}