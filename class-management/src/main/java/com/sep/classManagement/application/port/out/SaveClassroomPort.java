package com.sep.classManagement.application.port.out;

import com.sep.classManagement.domain.model.Classroom;

public interface SaveClassroomPort {
    void save(Classroom classroom);
}