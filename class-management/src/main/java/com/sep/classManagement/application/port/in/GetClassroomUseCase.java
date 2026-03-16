package com.sep.classManagement.application.port.in;

import com.sep.classManagement.adapter.in.web.dto.ClassroomResponse;
import com.sep.classManagement.application.port.in.query.GetClassroomQuery;


public interface GetClassroomUseCase {
    ClassroomResponse execute(GetClassroomQuery query);
}