package com.sep.learningContents.domain.model.question;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrueFalseConfig implements QuestionConfig {

    private boolean includeNotGiven;

    public enum TrueFalseAnswer {
        TRUE, FALSE, NOT_GIVEN
    }

    private TrueFalseAnswer correctAnswer;

    @Override
    public void validate() {
        if (correctAnswer == null) {
            throw new IllegalArgumentException("Correct answer is required");
        }
        
        // Nếu không bật chế độ Not Given thì đáp án không được là NOT_GIVEN
        if (!includeNotGiven && correctAnswer == TrueFalseAnswer.NOT_GIVEN) {
            throw new IllegalArgumentException("Cannot set correct answer as NOT_GIVEN when includeNotGiven is false");
        }
    }
}
