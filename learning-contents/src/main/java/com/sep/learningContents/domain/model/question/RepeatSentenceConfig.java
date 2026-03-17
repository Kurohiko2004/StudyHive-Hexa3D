package com.sep.learningContents.domain.model.question;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RepeatSentenceConfig implements QuestionConfig {
    private Integer maxRecordingTime; // In seconds
    private Integer maxRecordAttempts; // Number of allowed attempts

    @Override
    public void validate() {
        if (maxRecordingTime != null && maxRecordingTime <= 0) {
            throw new IllegalArgumentException("Max recording time must be positive");
        }
        if (maxRecordAttempts != null && maxRecordAttempts < 1) {
            throw new IllegalArgumentException("Max record attempts must be at least 1");
        }
    }
}
