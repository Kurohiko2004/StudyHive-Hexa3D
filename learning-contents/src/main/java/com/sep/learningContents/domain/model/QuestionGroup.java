package com.sep.learningContents.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class QuestionGroup {
    private String id;
    private String title;
    private String description;
    
    private String subjectId;
    private String skillId;
    private String levelId;
    
    private String instructions;
    private String passage;
    private String mediaUrl;
    
    private String authorId;
    private Instant createdAt;
    private Instant updatedAt;

    public static QuestionGroup createNew(
            String title,
            String description,
            String subjectId,
            String skillId,
            String levelId,
            String instructions,
            String passage,
            String mediaUrl,
            String authorId
    ) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (title.length() > 100) {
            throw new IllegalArgumentException("Title cannot exceed 100 characters");
        }
        if (passage != null && passage.length() > 2000) {
            throw new IllegalArgumentException("Passage cannot exceed 2000 characters");
        }

        return QuestionGroup.builder()
                .id(UUID.randomUUID().toString())
                .title(title)
                .description(description)
                .subjectId(subjectId)
                .skillId(skillId)
                .levelId(levelId)
                .instructions(instructions)
                .passage(passage)
                .mediaUrl(mediaUrl)
                .authorId(authorId)
                .createdAt(Instant.now())
                .build();
    }
}

