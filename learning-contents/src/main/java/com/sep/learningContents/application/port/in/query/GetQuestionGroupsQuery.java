package com.sep.learningContents.application.port.in.query;

import com.sep.commonModule.dto.BaseFilter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetQuestionGroupsQuery extends BaseFilter {
    private String subjectId;
    private String levelId;
    private String skillId;
    private String type;
}
