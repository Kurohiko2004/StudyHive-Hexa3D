package com.sep.classManagement.domain.model;

import com.sep.commonModule.exception.BusinessValidationException;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class Comment {
    private String id;
    private String postId;
    private String authorId;
    private String parentId;
    private String content;
    private Instant createdAt;
    private Instant updatedAt;


    public static Comment createNew(String postId, String authorId,String parentId, String content) {
        Comment post = Comment.builder()
                .id(UUID.randomUUID().toString())
                .postId(postId)
                .authorId(authorId)
                .parentId(parentId)
                .content(content)
                .createdAt(Instant.now())
                .build();

        post.validate();
        return post;
    }

    public void validate() {
        if(this.postId == null || this.postId.trim().isEmpty()){
            throw new BusinessValidationException("postId", "Post ID is required.");
        }

        if(this.authorId == null || this.authorId.trim().isEmpty()){
            throw new BusinessValidationException("authorId", "Author ID is required.");
        }

        if (this.content == null || this.content.trim().isEmpty()) {
            throw new BusinessValidationException("content", "Comment content cannot be empty.");
        }
        if (this.content.length() > 1000) {
            throw new BusinessValidationException("content", "Comment content cannot exceed 1000 characters.");
        }
    }

}
