package com.sep.sysAdmin.adapter.out.persistance;

import com.sep.commonModule.domain.model.EntityStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "subject")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectJpaEntity {

    @Id
    private String id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EntityStatus status;

    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

}