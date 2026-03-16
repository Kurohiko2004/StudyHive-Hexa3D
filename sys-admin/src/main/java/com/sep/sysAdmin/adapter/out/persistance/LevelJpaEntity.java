package com.sep.sysAdmin.adapter.out.persistance;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "level")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LevelJpaEntity {

    @Id
    private String id;

    @Column(name = "subject_id", nullable = false)
    private String subjectId;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

}