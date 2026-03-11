package com.sep.classManagement.adapter.out.persistance;

import com.sep.commonModule.domain.model.EntityStatus;
import com.sep.commonModule.adapter.out.persistance.BaseJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

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

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "subject_id", nullable = false)
    private String subjectId;

    @Column(name = "level_id", nullable = false)
    private String levelId;

    @Column(name = "teacher_id")
    private String teacherId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "classroom_schedule",
            joinColumns = @JoinColumn(name = "classroom_id") // Khóa ngoại
    )
    @Column(name = "day_of_week")
    private List<String> schedule;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EntityStatus status;
}