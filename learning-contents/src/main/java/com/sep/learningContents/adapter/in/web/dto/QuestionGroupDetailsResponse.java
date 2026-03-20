package com.sep.learningContents.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionGroupDetailsResponse {
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

    private List<QuestionGroupItemResponse> questions;
}
