package com.sep.learningContents.domain.model.question;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FillBlankConfig implements QuestionConfig {
    
    private List<Blank> blanks;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Blank {
        private int index;
        private String correctAnswer;
        private List<String> alternativeAnswers; // Các đáp án thay thế
        private boolean caseSensitive; // Phân biệt chữ hoa/thường
    }

    @Override
    public void validate() {
        if (blanks == null || blanks.isEmpty()) {
            throw new IllegalArgumentException("Must have at least one blank to fill");
        }
        for (Blank blank : blanks) {
            if (blank.getCorrectAnswer() == null || blank.getCorrectAnswer().trim().isEmpty()) {
                throw new IllegalArgumentException("Each blank must have a correct answer");
            }
        }
    }
}
