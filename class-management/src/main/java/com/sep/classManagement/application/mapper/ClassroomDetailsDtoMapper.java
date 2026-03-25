package com.sep.classManagement.application.mapper;

import com.sep.classManagement.adapter.in.web.dto.ClassroomDetailsResponse;
import com.sep.classManagement.adapter.in.web.dto.ClassroomResponse;
import com.sep.classManagement.domain.model.Classroom;
import com.sep.commonModule.dto.PageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClassroomDetailsDtoMapper {

    @Mapping(source = "schedule", target = "schedules")
    ClassroomDetailsResponse toResponse(Classroom domain);

    ClassroomDetailsResponse toDetailsResponse(Classroom classroom);
}