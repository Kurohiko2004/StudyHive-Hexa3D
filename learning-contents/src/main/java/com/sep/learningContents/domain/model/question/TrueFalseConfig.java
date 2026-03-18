package com.sep.learningContents.domain.model.question;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrueFalseConfig implements QuestionConfig {
    private boolean correctAnswer; // true = TRUE, false = FALSE
    private boolean includeNotGiven; // Allow "Not Given" option

    private boolean notGivenCorrect; // true = "Not Given" is the correct answer. Only valid if includeNotGiven is true.

    public enum AnswerType {
        TRUE, FALSE, NOT_GIVEN
    }
    
    // Simplification: We can store the correct answer as an Enum instead of boolean
    private AnswerType correctType;

    @Override
    public void validate() {
        if (correctType == null) {
            throw new IllegalArgumentException("Correct answer type is required");
        }
        if (correctType == AnswerType.NOT_GIVEN && !includeNotGiven) {
            throw new IllegalArgumentException("Cannot set correct answer as NOT_GIVEN when includeNotGiven is false");
        }
    }
}
