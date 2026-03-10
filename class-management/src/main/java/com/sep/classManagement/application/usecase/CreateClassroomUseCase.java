package com.sep.classManagement.application.usecase;

import com.sep.classManagement.application.port.in.command.CreateClassroomCommand;
import com.sep.classManagement.application.port.out.SaveClassroomPort;
import com.sep.classManagement.domain.model.Classroom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateClassroomUseCase implements com.sep.classManagement.application.port.in.CreateClassroomUseCase {

    private final SaveClassroomPort saveClassroomPort;

    @Override
    public String execute(CreateClassroomCommand command) {
        Classroom newClass = Classroom.createNew(
                command.getName(),
                command.getDescription(),
                command.getTeacherId()
        );

        newClass.validate();

        saveClassroomPort.save(newClass);

        return newClass.getId();
    }
}
