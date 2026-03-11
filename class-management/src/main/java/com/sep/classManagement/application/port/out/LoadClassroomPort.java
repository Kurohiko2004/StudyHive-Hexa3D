package com.sep.classManagement.application.port.out;

import com.sep.classManagement.domain.model.Classroom;
import com.sep.commonModule.dto.PageResponse;

import java.util.Optional;

public interface LoadClassroomPort {
    PageResponse<Classroom> loadClassrooms(int page, int size);
    Optional<Classroom> loadClassroomById(String id);
}
