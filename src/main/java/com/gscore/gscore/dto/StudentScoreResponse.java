package com.gscore.gscore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentScoreResponse {
    private String studentId;
    private String foreignId;
    private List<SubjectScoreDTO> scores;
}
