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
public class TopStudentScoreResponse {
    private String studentId;
    private String foreignId;
    private Double totalScore;
    private List<SubjectScoreDTO> scores;
}
