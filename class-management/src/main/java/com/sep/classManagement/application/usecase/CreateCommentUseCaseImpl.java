package com.sep.classManagement.application.usecase;

import com.sep.classManagement.application.port.in.CreateCommentUseCase;
import com.sep.classManagement.application.port.in.command.CreateCommentCommand;
import com.sep.classManagement.application.port.out.SaveCommentPort;
import com.sep.classManagement.domain.model.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCommentUseCaseImpl implements CreateCommentUseCase {
    private final SaveCommentPort saveCommentPort;

    @Override
    public String execute(CreateCommentCommand command) {
        Comment comment = Comment.createNew(command.getPostId(), command.getAuthorId(), command.getContent());
        saveCommentPort.saveComment(comment);
        return comment.getId();
    }
}
