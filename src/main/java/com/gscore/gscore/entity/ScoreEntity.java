package com.gscore.gscore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "score")
@Getter
@Setter
public class ScoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "subject_code")
    private SubjectEntity subject;

    @NotNull
    @Min(value = 0, message = "Score must be greater than 0")
    @Max(value = 10, message = "Score must be less than 10")
    private Double score;
}
