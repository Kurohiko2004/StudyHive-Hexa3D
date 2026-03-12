package com.sep.classManagement.application.port.in.query;

import com.sep.commonModule.dto.BaseFilter;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetClassroomsQuery extends BaseFilter {
    private String subjectId;
    private String levelId;
    private String teacherId;
    private String status;
}