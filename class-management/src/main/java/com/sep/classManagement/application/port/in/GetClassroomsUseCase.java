package com.sep.classManagement.application.port.in;

import com.sep.classManagement.application.port.in.query.GetClassroomsQuery;
import com.sep.classManagement.domain.model.Classroom;
import com.sep.commonModule.dto.PageResponse;

public interface GetClassroomsUseCase {
    PageResponse<Classroom> execute(GetClassroomsQuery query);
}