package com.sep.classManagement.application.usecase;

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

    @Override
    public PageResponse<Classroom> execute(GetClassroomsQuery query) {

        int page = query.getPage() > 0 ? query.getPage() - 1 : 0;
        int size = query.getSize() > 0 ? query.getSize() : 10;

        return loadClassroomPort.loadClassrooms(page, size);
    }
}