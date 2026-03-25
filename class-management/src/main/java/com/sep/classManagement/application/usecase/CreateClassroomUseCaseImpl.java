package com.sep.classManagement.application.usecase;

import com.sep.classManagement.application.port.in.CreateClassroomUseCase;
import com.sep.classManagement.application.port.in.command.CreateClassroomCommand;
import com.sep.classManagement.application.port.out.CheckUserPort;
import com.sep.classManagement.application.port.out.SaveClassroomPort;
import com.sep.classManagement.domain.model.Classroom;
import com.sep.commonModule.exception.BusinessValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateClassroomUseCaseImpl implements CreateClassroomUseCase {

    private final SaveClassroomPort saveClassroomPort;
    private final CheckUserPort checkTeacherPort;

    @Override
    @Transactional
    public String execute(CreateClassroomCommand command) {
        // Validate teacher
        if (command.getTeacherId() != null && !command.getTeacherId().isBlank()) {
            CheckUserPort.TeacherInfo teacherInfo = checkTeacherPort.getTeacherInfo(command.getTeacherId());
            if (!"TEACHER".equalsIgnoreCase(teacherInfo.role())) {
                throw new BusinessValidationException("teacherId", "User is not a teacher");
            }
            if (!"ACTIVE".equalsIgnoreCase(teacherInfo.status())) {
                throw new BusinessValidationException("teacherId", "Teacher is not active");
            }
        }

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