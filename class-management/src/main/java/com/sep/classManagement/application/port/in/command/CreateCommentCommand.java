package com.sep.classManagement.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentCommand {
    private String postId;
    private String authorId;
    private String parentId;

    @NotBlank(message = "Content cannot be blank")
    @Size(max = 1000, message = "Content cannot exceed 1000 characters")
    private String content;
}
