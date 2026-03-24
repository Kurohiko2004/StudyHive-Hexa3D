package com.sep.userService.adapter.in.grpc;

import com.sep.commonModule.grpc.user.v1.GetTeacherInfoRequest;
import com.sep.commonModule.grpc.user.v1.GetTeacherInfoResponse;
import com.sep.commonModule.grpc.user.v1.UserServiceGrpc;
import com.sep.userService.application.port.out.UserRepository;
import com.sep.userService.domain.model.User;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Optional;

@GrpcService
@RequiredArgsConstructor
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {

    private final UserRepository userRepository;

    @Override
    public void getTeacherInfo(GetTeacherInfoRequest request, StreamObserver<GetTeacherInfoResponse> responseObserver) {
        String userId = request.getUserId();
        
        Optional<User> userOpt = userRepository.findById(userId);
        
        if (userOpt.isEmpty()) {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("User not found with id: " + userId)
                    .asRuntimeException());
            return;
        }
        
        User user = userOpt.get();
        String roleStr = user.getRole() != null ? user.getRole().name() : "";
        String statusStr = user.getStatus() != null ? user.getStatus().name() : "";
        
        GetTeacherInfoResponse response = GetTeacherInfoResponse.newBuilder()
                .setUserId(user.getId())
                .setRole(roleStr)
                .setStatus(statusStr)
                .build();
                
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

