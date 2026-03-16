package com.sep.classManagement.adapter.out.persistance;

import com.sep.classManagement.domain.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    CommentJpaEntity toJpaEntity(Comment domain);

    Comment toDomain(CommentJpaEntity entity);

    List<Comment> toDomainList(List<CommentJpaEntity> entities);
}

