package com.sep.learningContents.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponse {
    private String type;
    private String subjectId;
    private String skillId;
    private String levelId;

    private String questionText;
    private Instant createdAt;
}
