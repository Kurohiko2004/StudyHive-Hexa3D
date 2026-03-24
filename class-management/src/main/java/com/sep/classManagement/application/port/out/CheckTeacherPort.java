package com.sep.classManagement.application.port.out;

public interface CheckTeacherPort {
    TeacherInfo getTeacherInfo(String teacherId);
    
    record TeacherInfo(String id, String role, String status) {}
}

