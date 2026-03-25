package com.sep.classManagement.application.usecase;

import com.sep.classManagement.application.port.in.DeleteClassroomUseCase;
import com.sep.classManagement.application.port.out.DeleteClassroomPort;
import com.sep.classManagement.application.port.out.LoadClassroomPort;
import com.sep.commonModule.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteClassroomUseCaseImpl implements DeleteClassroomUseCase {

    private final LoadClassroomPort loadClassroomPort;
    private final DeleteClassroomPort deleteClassroomPort;

    @Override
    @Transactional
    public String execute(String id) {
        loadClassroomPort.loadClassroomById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Classroom not found with id: " + id));

        deleteClassroomPort.delete(id);
        return id;
    }
}

