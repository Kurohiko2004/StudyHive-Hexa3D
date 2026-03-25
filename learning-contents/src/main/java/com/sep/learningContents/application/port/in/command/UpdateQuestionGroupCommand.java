package com.sep.learningContents.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateQuestionGroupCommand {
    private String id;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    private String title;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @NotBlank(message = "Subject is required")
    private String subjectId;

    @NotBlank(message = "Skill is required")
    private String skillId;

    @NotBlank(message = "Level is required")
    private String levelId;

    private String instructions;

    @Size(max = 2000, message = "Passage cannot exceed 2000 characters")
    private String passage;

    private String mediaUrl;
}

