package com.sep.classManagement.adapter.out.persistance;

import com.sep.classManagement.application.port.out.LoadClassroomPort;
import com.sep.classManagement.application.port.out.SaveClassroomPort;
import com.sep.classManagement.domain.model.Classroom;
import com.sep.commonModule.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClassroomPersistenceAdapter implements SaveClassroomPort, LoadClassroomPort {

    private final ClassroomRepository classroomRepository;
    private final ClassroomMapper mapper;

    @Override
    public void save(Classroom classroom) {
        ClassroomJpaEntity entity = mapper.toJpaEntity(classroom);
//        System.err.println("🚨🚨🚨 CHUẨN BỊ SAVE VÀO DB 🚨🚨🚨");
//        System.err.println("Subject ID: [" + entity.getSubjectId() + "]");
//        System.err.println("Level ID  : [" + entity.getLevelId() + "]");
        classroomRepository.save(entity);
    }

    @Override
    public PageResponse<Classroom> loadClassrooms(int page, int size) {
        int validPage = page > 0 ? page - 1 : 0;
        Pageable pageable = PageRequest.of(validPage, size, Sort.by("createdAt").descending());
        Page<ClassroomJpaEntity> entityPage = classroomRepository.findAll(pageable);
        List<Classroom> classrooms = entityPage.getContent().stream()
                .map(mapper::toDomain)
                .toList();

        return PageResponse.<Classroom>builder()
                .data(classrooms)
                .currentPage(entityPage.getNumber() + 1)
                .totalPages(entityPage.getTotalPages())
                .totalElements(entityPage.getTotalElements())
                .hasNext(entityPage.hasNext())
                .build();
    }

    @Override
    public Optional<Classroom> loadClassroomById(String id) {
        return classroomRepository.findById(id).map(mapper::toDomain);
    }
}