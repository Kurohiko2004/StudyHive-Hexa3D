package com.sep.classManagement.application.usecase;

import com.sep.classManagement.application.port.in.CreateClassPostUseCase;
import com.sep.classManagement.application.port.in.command.CreateClassPostCommand;
import com.sep.classManagement.application.port.out.SaveClassPostPort;
import com.sep.classManagement.domain.model.ClassPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateClassPostUseCaseImpl implements CreateClassPostUseCase {

    private final SaveClassPostPort saveClassPostPort;

    @Override
    public String execute(CreateClassPostCommand command) {


        ClassPost newPost = ClassPost.createNew(
                command.getTitle(),
                command.getContent(),
                command.getClassroomId(),
                command.getAuthorId(),
                command.getAttachments() // Nhận trực tiếp list string URL từ command
        );

        saveClassPostPort.save(newPost);
        return newPost.getId();
    }
}