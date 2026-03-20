package com.sep.learningContents.application.port.in.query;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetQuestionQuery {
    String id;
}
