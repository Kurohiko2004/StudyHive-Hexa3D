package com.sep.learningContents.domain.model.question;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchingConfig implements QuestionConfig {

    private List<MatchingPair> pairs;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MatchingPair {
        private String prompt;    // Vế trái (đề bài/câu hỏi)
        private String match; // Vế phải (đáp án đúng tương ứng)
    }

    @Override
    public void validate() {
        if (pairs == null || pairs.size() < 2) {
            throw new IllegalArgumentException("Matching question must have at least 2 pairs");
        }
        for (MatchingPair pair : pairs) {
             if (pair.getPrompt() == null || pair.getPrompt().isBlank()) {
                 throw new IllegalArgumentException("Prompt cannot be empty");
             }
             if (pair.getMatch() == null || pair.getMatch().isBlank()) {
                 throw new IllegalArgumentException("Match answer cannot be empty");
             }
        }
    }
}
