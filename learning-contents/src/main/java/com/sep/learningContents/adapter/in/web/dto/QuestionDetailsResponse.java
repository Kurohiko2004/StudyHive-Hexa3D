package com.sep.learningContents.adapter.in.web.dto;

import com.sep.learningContents.domain.model.question.QuestionConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDetailsResponse {
    private Integer index;
    private String type;
    private String subjectId;
    private String groupId;
    private String skillId;
    private String levelId;
    private Double defaultPoint;

    private String questionText;
    private String additionalInstructions;
    private String mediaUrl;

    private String authorId;
    private Instant createdAt;
    private Instant updatedAt;
    private QuestionConfig specificConfig;
}
