package com.sep.classManagement.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateClassPostCommand {

    private String classroomId;

    private String authorId;
    @NotBlank(message = "Post title cannot be blank")
    @Size(max = 255, message = "Post title cannot exceed 255 characters")
    private String title;

    @NotBlank(message = "Post content cannot be blank")
    @Size(max = 1200, message = "Post content cannot exceed 1200 characters")
    private String content;

    private List<String> attachments;
}