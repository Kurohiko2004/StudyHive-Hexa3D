package com.sep.classManagement.application.port.out;

import com.sep.classManagement.domain.model.Comment;

public interface SaveCommentPort {
    void saveComment(Comment comment);
}
