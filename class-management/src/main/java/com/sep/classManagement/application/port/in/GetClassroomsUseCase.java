package com.sep.classManagement.application.port.in;

import com.sep.classManagement.adapter.in.web.dto.ClassroomResponse;
import com.sep.classManagement.application.port.in.query.GetClassroomsQuery;
import com.sep.commonModule.dto.PageResponse;

public interface GetClassroomsUseCase {
    PageResponse<ClassroomResponse> execute(GetClassroomsQuery query);
}