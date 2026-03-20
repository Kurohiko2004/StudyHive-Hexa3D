package com.sep.learningContents.adapter.in.web.dto;

import com.sep.learningContents.domain.model.question.QuestionConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionGroupItemResponse {
    private Integer index;
    private String type;
    private Double point;
    private QuestionConfig specificConfig;
}

