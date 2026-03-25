package com.sep.classManagement.application.usecase;

import com.sep.classManagement.adapter.in.web.dto.ClassroomDetailsResponse;
import com.sep.classManagement.application.mapper.ClassroomDetailsDtoMapper;
import com.sep.classManagement.application.port.in.UpdateClassroomUseCase;
import com.sep.classManagement.application.port.in.command.UpdateClassroomCommand;
import com.sep.classManagement.application.port.out.LoadClassroomPort;
import com.sep.classManagement.application.port.out.SaveClassroomPort;
import com.sep.classManagement.domain.model.Classroom;
import com.sep.commonModule.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateClassroomUseCaseImpl implements UpdateClassroomUseCase {

    private final LoadClassroomPort loadClassroomPort;
    private final SaveClassroomPort saveClassroomPort;
    private final ClassroomDetailsDtoMapper mapper;

    @Override
    @Transactional
    public ClassroomDetailsResponse execute(UpdateClassroomCommand command) {
        Classroom classroom = loadClassroomPort.loadClassroomById(command.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Classroom not found with id: " + command.getId()));

        classroom.update(
                command.getName(),
                command.getDescription(),
                command.getSubjectId(),
                command.getLevelId(),
                command.getTeacherId(),
                command.getSchedule(),
                command.getStatus()
        );

        saveClassroomPort.save(classroom);

        return mapper.toDetailsResponse(classroom);
    }
}
