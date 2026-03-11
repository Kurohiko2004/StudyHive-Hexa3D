package com.sep.classManagement.adapter.in.web.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class ClassroomResponse {
    private String id;
    private String name;
    private String code;
    private String description;
    private String status;
    private String teacherId;
    private Instant createdAt;
    private String subjectId;
    private String levelId;
    private List<String> schedule;
}