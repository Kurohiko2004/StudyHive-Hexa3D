package com.sep.learningContents.application.port.in.command;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.sep.learningContents.domain.model.question.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateQuestionCommand {

    private String id;

    @NotBlank(message = "Question type is required")
    private String type;

    @NotBlank(message = "Subject ID is required")
    private String subjectId;

    private String groupId;

    @NotBlank(message = "Skill ID is required")
    private String skillId;

    @NotBlank(message = "Level ID is required")
    private String levelId;

    @Builder.Default
    private Double defaultPoint = 10.0;

    @NotBlank(message = "Question text is required")
    private String questionText;

    private String additionalInstructions;
    private String mediaUrl;

    @JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME, 
        include = JsonTypeInfo.As.PROPERTY, 
        property = "type"
    )
    @JsonSubTypes({
        @JsonSubTypes.Type(value = MultipleChoiceConfig.class, name = "MULTIPLE_CHOICE"),
        @JsonSubTypes.Type(value = EssayConfig.class, name = "ESSAY"),
        @JsonSubTypes.Type(value = FillBlankConfig.class, name = "FILL_IN_THE_BLANK"),
        @JsonSubTypes.Type(value = TrueFalseConfig.class, name = "TRUE_FALSE"),
        @JsonSubTypes.Type(value = OrderingConfig.class, name = "ORDERING"),
        @JsonSubTypes.Type(value = MatchingConfig.class, name = "MATCHING"),
        @JsonSubTypes.Type(value = RepeatSentenceConfig.class, name = "REPEAT_SENTENCE")
    })
    private QuestionConfig config;
}

