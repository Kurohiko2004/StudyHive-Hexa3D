package com.sep.classManagement.application.port.out;

import com.sep.classManagement.domain.model.Comment;

public interface LoadCommentPort {
    Comment loadComment(String commentId);
}

