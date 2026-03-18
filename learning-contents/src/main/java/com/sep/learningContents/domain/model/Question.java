package com.sep.learningContents.domain.model;

import com.sep.learningContents.domain.model.question.QuestionConfig;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class Question {
    private String id;
    private String type; // ESSAY, MULTIPLE_CHOICE
    private String subjectId;
    private String groupId;
    private String skillId; // READING, LISTENING, WRITING, SPEAKING
    private String levelId;
    private Double defaultPoint;
    
    private String questionText;
    private String additionalInstructions;
    private String mediaUrl;

    private String authorId;
    private Instant createdAt;
    private Instant updatedAt;
    
    // Cấu hình linh động tùy loại câu hỏi
    private QuestionConfig specificConfig;

    public static Question createNew(
            String type, 
            String subjectId,
            String groupId,
            String skillId,
            String levelId,
            Double defaultPoint,
            String questionText,
            String additionalInstructions,
            String mediaUrl,
            String authorId,
            QuestionConfig config
    ) {
        if (config == null) {
            throw new IllegalArgumentException("Question config is required");
        }
        // Gọi validate của config con
        config.validate();

        Question question = Question.builder()
                .id(UUID.randomUUID().toString())
                .type(type)
                .subjectId(subjectId)
                .groupId(groupId)
                .skillId(skillId)
                .levelId(levelId)
                .defaultPoint(defaultPoint != null ? defaultPoint : 10.0)
                .questionText(questionText)
                .additionalInstructions(additionalInstructions)
                .mediaUrl(mediaUrl)
                .authorId(authorId)
                .specificConfig(config)
                .createdAt(Instant.now())
                .build();
        
        question.validate();
        return question;
    }

    public void validate() {
        if (questionText == null || questionText.isBlank()) {
            throw new IllegalArgumentException("Question text cannot be empty");
        }
        if (type == null || type.isBlank()) {
             throw new IllegalArgumentException("Question type is required");
        }
        if (subjectId == null || subjectId.isBlank()) {
            throw new IllegalArgumentException("Subject ID is required");
        }
        if (skillId == null || skillId.isBlank()) {
            throw new IllegalArgumentException("Skill ID is required");
        }
        if (levelId == null || levelId.isBlank()) {
            throw new IllegalArgumentException("Level ID/Difficulty is required");
        }
    }
}