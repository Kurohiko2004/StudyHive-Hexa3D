package com.sep.classManagement.application.usecase;

import com.sep.classManagement.application.port.in.CreateCommentUseCase;
import com.sep.classManagement.application.port.in.command.CreateCommentCommand;
import com.sep.classManagement.application.port.out.SaveCommentPort;
import com.sep.classManagement.application.port.out.LoadCommentPort;
import com.sep.classManagement.domain.model.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCommentUseCaseImpl implements CreateCommentUseCase {
    private final SaveCommentPort saveCommentPort;
    private final LoadCommentPort loadCommentPort;

    @Override
    public String execute(CreateCommentCommand command) {
        String parentId = (command.getParentId() != null && !command.getParentId().isBlank())
                ? command.getParentId()
                : null;

        if (parentId != null) {
            Comment parentComment = loadCommentPort.loadComment(parentId);
            if (!parentComment.getPostId().equals(command.getPostId())) {
                throw new IllegalArgumentException("Parent comment does not belong to the same post");
            }
        }
        
        Comment comment = Comment.createNew(command.getPostId(), command.getAuthorId(), parentId, command.getContent());
        saveCommentPort.saveComment(comment);
        return comment.getId();
    }
}
