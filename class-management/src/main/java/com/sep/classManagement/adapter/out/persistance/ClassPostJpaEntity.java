package com.sep.classManagement.adapter.out.persistance;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "class_posts")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassPostJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "classroom_id", nullable = false)
    private String classroomId;

    @Column(name = "author_id", nullable = false)
    private String authorId;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "content", nullable = false, length = 1200)
    private String content;

    @Column(name = "is_pinned")
    private Boolean isPinned;

    @Column(name = "allow_comments")
    private Boolean allowComments;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private java.time.Instant createdAt;

    @Column(name = "updated_at")
    private java.time.Instant updatedAt;

    @ElementCollection
    @CollectionTable(
            name = "class_post_attachments",
            joinColumns = @JoinColumn(name = "post_id")
    )
    @Column(name = "file_url")
    private List<String> attachmentUrls;
}