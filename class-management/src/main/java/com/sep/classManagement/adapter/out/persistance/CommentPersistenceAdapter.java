package com.sep.classManagement.adapter.out.persistance;

import com.sep.classManagement.application.port.out.LoadCommentPort;
import com.sep.classManagement.application.port.out.SaveCommentPort;
import com.sep.classManagement.domain.model.Comment;
import com.sep.commonModule.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentPersistenceAdapter implements SaveCommentPort, LoadCommentPort {
    private final CommentRepository repository;
    private final CommentMapper mapper;

    @Override
    public void saveComment(Comment comment) {
        repository.save(mapper.toJpaEntity(comment));
    }

    @Override
    public Comment loadComment(String commentId) {
        return repository.findById(commentId)
                .map(mapper::toDomain)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with ID " + commentId + " not found"));
    }
}
