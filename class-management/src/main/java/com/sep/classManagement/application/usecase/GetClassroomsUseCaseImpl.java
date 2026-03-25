package com.sep.classManagement.application.usecase;

import com.sep.classManagement.adapter.in.web.dto.ClassroomResponse;
import com.sep.classManagement.application.mapper.ClassroomDtoMapper;
import com.sep.classManagement.application.port.in.GetClassroomsUseCase;
import com.sep.classManagement.application.port.in.query.GetClassroomsQuery;
import com.sep.classManagement.application.port.out.LoadClassroomPort;
import com.sep.classManagement.domain.model.Classroom;
import com.sep.commonModule.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GetClassroomsUseCaseImpl implements GetClassroomsUseCase {

    private final LoadClassroomPort loadClassroomPort;
    private final ClassroomDtoMapper mapper;

    @Override
    public PageResponse<ClassroomResponse> execute(GetClassroomsQuery query) {

        long start = System.currentTimeMillis(); // Bấm giờ

        PageResponse<Classroom> domainPage = loadClassroomPort.loadClassrooms(query);
        long time1 = System.currentTimeMillis();
//        System.out.println("Thời gian lấy Redis/DB: " + (time1 - start) + " ms");

        PageResponse<ClassroomResponse> response = mapper.toPageResponse(domainPage);
        long time2 = System.currentTimeMillis();
//        System.out.println("Thời gian chạy Mapper/gRPC mất: " + (time2 - time1) + " ms");

        return response;
    }
}