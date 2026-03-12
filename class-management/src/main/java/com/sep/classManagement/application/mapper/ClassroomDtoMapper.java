package com.sep.classManagement.application.mapper;

import com.sep.classManagement.adapter.in.web.dto.ClassroomResponse;
import com.sep.classManagement.domain.model.Classroom;
import com.sep.commonModule.dto.PageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClassroomDtoMapper {

    @Mapping(source = "schedule", target = "schedules")
    ClassroomResponse toResponse(Classroom domain);
    Classroom toDomain(ClassroomResponse response);
    PageResponse<ClassroomResponse> toPageResponse(PageResponse<Classroom> domainPage);
}