package com.sep.classManagement.adapter.out.persistance;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "class_post_comments")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentJpaEntity {

    @Id
    private String id;

    @Column(name = "post_id", nullable = false)
    private String postId;

    @Column(name = "author_id", nullable = false)
    private String authorId;

    @Column(name = "content", nullable = false, length = 1000)
    private String content;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}

