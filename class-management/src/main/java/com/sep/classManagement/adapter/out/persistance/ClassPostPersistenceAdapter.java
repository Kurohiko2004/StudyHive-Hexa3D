package com.sep.classManagement.adapter.out.persistance;

import com.sep.classManagement.application.port.out.SaveClassPostPort;
import com.sep.classManagement.domain.model.ClassPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClassPostPersistenceAdapter implements SaveClassPostPort {

    private final ClassPostMapper mapper;
    private final ClassPostRepository repository;

    @Override
    public void save(ClassPost post) {
        ClassPostJpaEntity entity = mapper.toJpaEntity(post);
        repository.save(entity);
    }
}
