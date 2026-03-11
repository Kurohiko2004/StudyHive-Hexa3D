package com.sep.classManagement.adapter.out.persistance;

import com.sep.commonModule.adapter.out.persistance.BaseJpaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "classroom")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomJpaEntity extends BaseJpaEntity {

    @Id
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "teacher_id", nullable = false)
    private String teacherId;

    @Column(name = "status", nullable = false)
    private String status;
}