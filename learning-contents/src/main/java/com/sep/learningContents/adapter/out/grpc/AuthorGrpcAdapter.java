package com.sep.learningContents.adapter.out.grpc;

import com.sep.commonModule.exception.BusinessValidationException;
import com.sep.commonModule.grpc.user.v1.GetUserRequest;
import com.sep.commonModule.grpc.user.v1.GetUserResponse;
import com.sep.commonModule.grpc.user.v1.UserSummary;
import com.sep.commonModule.grpc.user.v1.UserServiceGrpc;
import com.sep.learningContents.application.port.out.CheckUserPort;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Component
public class AuthorGrpcAdapter implements CheckUserPort {

    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userServiceStub;

    @Override
    public AuthorInfo getAuthorInfo(String authorId) {
        try {
            // 1. Prepare generic User Request
            GetUserRequest request = GetUserRequest.newBuilder()
                    .setUserId(authorId)
                    .build();

            // 2. Call gRPC to get User Profile
            GetUserResponse response = userServiceStub.getUserProfile(request);
            UserSummary user = response.getUser();

            // 3. Validate Role locally (Adapter Logic)
            // Assuming only TEACHER or ADMIN can be author. 
            // Or maybe just check if user exists.
            // Based on ClassManagement "Teacher", let's be strict for now or relaxed.
            // The prompt says "follow logic like class management", which validates role "TEACHER".
            // So we probably want TEACHER role.
            if (!"TEACHER".equalsIgnoreCase(user.getRole())) {
                throw new BusinessValidationException("authorId", 
                    "User with id " + authorId + " is not a TEACHER. Current role: " + user.getRole());
            }

            // 4. Map to domain object
            return new AuthorInfo(
                    user.getId(),
                    user.getRole(),
                    user.getStatus()
            );

        } catch (StatusRuntimeException e) {
            switch (e.getStatus().getCode()) {
                case NOT_FOUND:
                    throw new BusinessValidationException("authorId", "Author (User) not found");
                case UNAVAILABLE:
                    throw new BusinessValidationException("authorId", "User service is unavailable");
                default:
                    throw new BusinessValidationException("authorId", "Error checking author info: " + e.getStatus().getDescription());
            }
        }
    }
}

