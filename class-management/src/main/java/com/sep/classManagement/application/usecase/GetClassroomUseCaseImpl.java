package com.sep.classManagement.application.usecase;

import com.sep.classManagement.adapter.in.web.dto.ClassroomResponse;
import com.sep.classManagement.application.mapper.ClassroomDtoMapper;
import com.sep.classManagement.application.port.in.GetClassroomUseCase;
import com.sep.classManagement.application.port.in.query.GetClassroomQuery;
import com.sep.classManagement.application.port.out.LoadClassroomPort;
import com.sep.classManagement.domain.model.Classroom;
import com.sep.commonModule.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetClassroomUseCaseImpl implements GetClassroomUseCase {

    private final LoadClassroomPort loadClassroomPort;
    private final ClassroomDtoMapper mapper;

    @Override
    public ClassroomResponse execute(GetClassroomQuery query) {
        Classroom domain = loadClassroomPort.loadClassroomById(query.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Classroom with ID " + query.getId() + " not found"));

        return mapper.toResponse(domain);
    }
}