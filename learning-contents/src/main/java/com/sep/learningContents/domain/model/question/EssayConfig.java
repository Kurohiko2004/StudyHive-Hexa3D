package com.sep.learningContents.domain.model.question;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EssayConfig implements QuestionConfig {
    private int minWords;
    private int maxWords;

    @Override
    public void validate() {
        if (minWords < 10) throw new IllegalArgumentException("Min words must be >= 10");
        if (maxWords > 5000) throw new IllegalArgumentException("Max words must be <= 5000");
        if (minWords > maxWords) throw new IllegalArgumentException("MSG30: Min cannot be greater than Max");
    }
}
