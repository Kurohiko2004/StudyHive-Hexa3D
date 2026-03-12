package com.sep.classManagement.application.port.out;

import com.sep.classManagement.application.port.in.query.GetClassroomsQuery;
import com.sep.classManagement.domain.model.Classroom;
import com.sep.commonModule.dto.PageResponse;

import java.util.Optional;

public interface LoadClassroomPort {
    PageResponse<Classroom> loadClassrooms(GetClassroomsQuery query);
    Optional<Classroom> loadClassroomById(String id);
}
