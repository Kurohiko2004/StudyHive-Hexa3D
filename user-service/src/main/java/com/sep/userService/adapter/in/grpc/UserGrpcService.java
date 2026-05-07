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

        User userInfo = userOpt.get();
        UserSummary userSummary = mapToUserSummary(userInfo);

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

    private UserSummary mapToUserSummary(User userInfo) {
        return UserSummary.newBuilder()
                .setId(userInfo.getId())
                .setFullName(userInfo.getFullName() != null ? userInfo.getFullName() : "")
                .setEmail(userInfo.getEmail() != null ? userInfo.getEmail() : "")
                .setRole(userInfo.getRole() != null ? userInfo.getRole().name() : "")
                .setStatus(userInfo.getStatus() != null ? userInfo.getStatus().name() : "")
                .build();
    }
}
