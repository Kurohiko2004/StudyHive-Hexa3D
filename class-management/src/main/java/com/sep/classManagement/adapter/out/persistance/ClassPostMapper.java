package com.sep.classManagement.adapter.out.persistance;

import com.sep.classManagement.domain.model.ClassPost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClassPostMapper {

    @Mapping(target = "attachmentUrls", source = "attachments")
    ClassPostJpaEntity toJpaEntity(ClassPost domain);

    @Mapping(target = "attachments", source = "attachmentUrls")
    ClassPost toDomain(ClassPostJpaEntity entity);
}