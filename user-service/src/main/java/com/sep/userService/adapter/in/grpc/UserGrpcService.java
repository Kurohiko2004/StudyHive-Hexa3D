package com.sep.userService.adapter.in.grpc;

import com.sep.commonModule.grpc.user.v1.*;
import com.sep.userService.application.port.out.UserRepository;
import com.sep.userService.domain.model.User;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@GrpcService
@RequiredArgsConstructor
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {

    private final UserRepository userRepository;

    @Override
    public void getUserProfile(GetUserRequest request, StreamObserver<GetUserResponse> responseObserver) {
        String userId = request.getUserId();

        Optional<User> userOpt = userRepository.findById(userId);

        if (userOpt.isEmpty()) {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("User not found with id: " + userId)
                    .asRuntimeException());
            return;
        }

        User user = userOpt.get();
        UserSummary userSummary = mapToUserSummary(user);

        GetUserResponse response = GetUserResponse.newBuilder()
                .setUser(userSummary)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getUsersByIds(GetUsersByIdsRequest request, StreamObserver<GetUsersResponse> responseObserver) {
        List<String> userIds = request.getUserIdsList();

        // Note: Ideally, repository should support findAllByIds for better performance
        List<UserSummary> userSummaries = userIds.stream()
                .map(userRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(this::mapToUserSummary)
                .collect(Collectors.toList());

        GetUsersResponse response = GetUsersResponse.newBuilder()
                .addAllUsers(userSummaries)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private UserSummary mapToUserSummary(User user) {
        return UserSummary.newBuilder()
                .setId(user.getId())
                .setFullName(user.getFullName() != null ? user.getFullName() : "")
                .setEmail(user.getEmail() != null ? user.getEmail() : "")
                .setRole(user.getRole() != null ? user.getRole().name() : "")
                .setStatus(user.getStatus() != null ? user.getStatus().name() : "")
                .build();
    }
}

