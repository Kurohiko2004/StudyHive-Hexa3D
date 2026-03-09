package com.sep.classManagement.adapter.out.persistance;

import com.sep.classManagement.application.port.out.SaveClassroomPort;
import com.sep.classManagement.domain.model.Classroom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClassroomPersistenceAdapter implements SaveClassroomPort {

    private final ClassroomRepository classroomRepository;
    private final ClassroomMapper mapper;

    @Override
    public void save(Classroom classroom) {
        ClassroomJpaEntity entity = mapper.toJpaEntity(classroom);
        classroomRepository.save(entity);
    }
}