package com.sep.classManagement.adapter.out.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentJpaEntity, String> {
    List<CommentJpaEntity> findAllByPostId(String postId);
}

