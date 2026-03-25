package com.sep.learningContents.application.port.out;

public interface CheckUserPort {
    AuthorInfo getAuthorInfo(String authorId);

    record AuthorInfo(String id, String role, String status) {}
}

