package com.sep.commonModule.application.usecase;

import com.sep.commonModule.application.command.CreateUserCommand;
import com.sep.commonModule.application.port.in.CreateUserUseCase;
import com.sep.commonModule.domain.model.User;

public class CreateUserUseCaseImpl implements CreateUserUseCase {
    @Override
    public User execute(CreateUserCommand command) {

    }
}
