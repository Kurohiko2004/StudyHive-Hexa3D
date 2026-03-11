package com.sep.commonModule.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@Setter
@SuperBuilder
public abstract class BaseDomainModel {
    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String updatedBy;
    private Integer version;

    public BaseDomainModel() {
    }

    public BaseDomainModel(Instant createdAt, String createdBy, Instant updatedAt, String updatedBy, Integer version) {
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.version = version;
    }
}