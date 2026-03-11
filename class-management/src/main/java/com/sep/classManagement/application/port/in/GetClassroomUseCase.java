package com.sep.classManagement.application.port.in;

import com.sep.classManagement.application.port.in.query.GetClassroomQuery;
import com.sep.classManagement.domain.model.Classroom;

public interface GetClassroomUseCase {
    Classroom execute(GetClassroomQuery query);
}