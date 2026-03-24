package com.sep.classManagement.adapter.out.grpc;

import com.sep.classManagement.application.port.out.CheckTeacherPort;
import com.sep.commonModule.exception.BusinessValidationException;
import com.sep.commonModule.grpc.user.v1.GetTeacherInfoRequest;
import com.sep.commonModule.grpc.user.v1.GetTeacherInfoResponse;
import com.sep.commonModule.grpc.user.v1.UserServiceGrpc;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Component
public class TeacherGrpcAdapter implements CheckTeacherPort {

    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userServiceStub;

    @Override
    public TeacherInfo getTeacherInfo(String teacherId) {
        try {
            GetTeacherInfoRequest request = GetTeacherInfoRequest.newBuilder()
                    .setUserId(teacherId)
                    .build();

            GetTeacherInfoResponse response = userServiceStub.getTeacherInfo(request);

            return new TeacherInfo(
                    response.getUserId(),
                    response.getRole(),
                    response.getStatus()
            );
        } catch (StatusRuntimeException e) {
            switch (e.getStatus().getCode()) {
                case NOT_FOUND:
                    throw new BusinessValidationException("teacherId", "Teacher not found");
                case UNAVAILABLE:
                    throw new BusinessValidationException("teacherId", "User service is unavailable");
                default:
                    throw new BusinessValidationException("teacherId", "Error checking teacher info: " + e.getStatus().getDescription());
            }
        }
    }
}

