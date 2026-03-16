package com.sep.classManagement.application.port.out;

import com.sep.classManagement.domain.model.ClassPost;

public interface SaveClassPostPort {
    void save(ClassPost post);
}
