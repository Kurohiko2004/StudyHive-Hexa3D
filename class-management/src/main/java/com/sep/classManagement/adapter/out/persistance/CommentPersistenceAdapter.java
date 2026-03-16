package com.sep.classManagement.adapter.out.persistance;

import com.sep.classManagement.application.port.out.SaveCommentPort;
import com.sep.classManagement.domain.model.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentPersistenceAdapter implements SaveCommentPort {
    private final CommentRepository repository;
    private final CommentMapper mapper;

    @Override
    public void saveComment(Comment comment) {
        repository.save(mapper.toJpaEntity(comment));
    }
}
