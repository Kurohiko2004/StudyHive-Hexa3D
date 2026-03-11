package com.sep.classManagement.application.port.in.query;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetClassroomsQuery {
    int page;
    int size;
}