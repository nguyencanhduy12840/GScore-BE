package com.gscore.gscore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "student")
@Getter
@Setter
public class StudentEntity {
    @Id
    private String studentId;

    private String foreignId;
}
