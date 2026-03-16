package com.sep.classManagement.application.usecase;

import com.sep.classManagement.adapter.in.web.dto.ClassroomResponse;
import com.sep.classManagement.application.mapper.ClassroomDtoMapper;
import com.sep.classManagement.application.port.in.GetClassroomsUseCase;
import com.sep.classManagement.application.port.in.query.GetClassroomsQuery;
import com.sep.classManagement.application.port.out.LoadClassroomPort;
import com.sep.classManagement.domain.model.Classroom;
import com.sep.commonModule.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetClassroomsUseCaseImpl implements GetClassroomsUseCase {

    private final LoadClassroomPort loadClassroomPort;
    private final ClassroomDtoMapper mapper;

    @Override
    public PageResponse<ClassroomResponse> execute(GetClassroomsQuery query) {
        PageResponse<Classroom> domainPage = loadClassroomPort.loadClassrooms(query);

        return mapper.toPageResponse(domainPage);
    }
}