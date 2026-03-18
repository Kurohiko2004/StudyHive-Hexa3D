package com.sep.learningContents.adapter.out.persistance;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "learning_questions") // Use a clearer table name
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionJpaEntity {

    @Id
    @Column(length = 36)
    private String id;

    @Column(nullable = false, length = 50)
    private String type; // ESSAY, MULTIPLE_CHOICE...

    @Column(name = "subject_id", nullable = false)
    private String subjectId;

    @Column(nullable = false, length = 50)
    private String skill; // READING, LISTENING...

    @Column(name = "default_point", nullable = false)
    private Double defaultPoint;

    @Column(name = "question_text", columnDefinition = "TEXT", nullable = false)
    private String questionText;

    @Column(name = "additional_instructions", columnDefinition = "TEXT")
    private String additionalInstructions;

    @Column(name = "media_url", length = 500)
    private String mediaUrl;

    @Column(name = "author_id", nullable = false)
    private String authorId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "config_json", columnDefinition = "JSON")
    private String specificConfig;
    
    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }
}
