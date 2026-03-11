package com.sep.commonModule.domain.model;

/**
 * A general-purpose status enum for domain entities.
 */
public enum EntityStatus {
    PENDING,
    ACTIVE,
    INACTIVE// The entity is marked for deletion.
}