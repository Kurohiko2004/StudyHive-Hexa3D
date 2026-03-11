package com.sep.classManagement.application.usecase;

import com.sep.classManagement.application.port.in.CreateClassroomUseCase;
import com.sep.classManagement.application.port.in.command.CreateClassroomCommand;
import com.sep.classManagement.application.port.out.SaveClassroomPort;
import com.sep.classManagement.domain.model.Classroom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateClassroomUseCaseImpl implements CreateClassroomUseCase {

    private final SaveClassroomPort saveClassroomPort;

    @Override
    @Transactional
    public String execute(CreateClassroomCommand command) {
        Classroom newClass = Classroom.createNew(
                command.getName(),
                command.getDescription(),
                command.getSubjectId(),
                command.getLevelId(),
                command.getTeacherId(),
                command.getSchedule()
        );

        saveClassroomPort.save(newClass);

        return newClass.getId();
    }

}