package com.sep.learningContents.domain.model.question;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MultipleChoiceConfig implements QuestionConfig {

    private boolean allowMultipleAnswers; // Cho phép chọn nhiều đáp án

    private List<Option> options;

    @Override
    public void validate() {
        if (options == null || options.size() < 2) {
            throw new IllegalArgumentException("Multiple choice must have at least 2 options");
        }
        if (options.size() > 10) {
            throw new IllegalArgumentException("Maximum 10 options allowed");
        }
        for (Option option : options) {
            if (option.getText() != null && option.getText().length() > 500) {
                throw new IllegalArgumentException("Option content cannot exceed 500 characters");
            }
        }
        boolean hasCorrect = options.stream().anyMatch(Option::isCorrect);
        if (!hasCorrect) {
            throw new IllegalArgumentException("Must have at least 1 correct answer");
        }
    }
}
