package com.sep.classManagement.domain.model;

import com.sep.commonModule.domain.model.EntityStatus;
import com.sep.commonModule.exception.BusinessValidationException;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class ClassPost {
    private String id;
    private String classroomId;
    private String authorId;
    private String title;
    private String content;
    private List<String> attachments;

    private EntityStatus status;
    private Boolean isPinned;
    private Boolean allowComments;

    private Instant createdAt;
    private Instant updatedAt;


    public static ClassPost createNew(String title, String content, String classroomId, String authorId, List<String> attachments ) {
        ClassPost post = ClassPost.builder()
                .id(UUID.randomUUID().toString())
                .title(title)
                .content(content)
                .classroomId(classroomId)
                .authorId(authorId)
                .attachments(attachments)
                .status(EntityStatus.ACTIVE)
                .isPinned(Boolean.FALSE)
                .allowComments(Boolean.TRUE)
                .createdAt(Instant.now())
                .build();

        post.validate();
        return post;
    }

    public void validate() {
        if (title == null || title.trim().isEmpty()) {
            throw new BusinessValidationException("title", "Title cannot be empty");
        }
        if (title.length() > 255) {
            throw new BusinessValidationException("title", "Title length must be less than 255 characters");
        }
        if (content == null || content.trim().isEmpty()) {
            throw new BusinessValidationException("content", "Content cannot be empty");
        }
        if (content.length() > 1200) {
            throw new BusinessValidationException("content", "Content length must be less than 1200 characters");
        }
    }

}
