package com.gscore.gscore.service;

import com.gscore.gscore.dto.StudentScoreResponse;
import com.gscore.gscore.dto.TopStudentScoreResponse;

import java.util.List;

public interface IStudentService {
    StudentScoreResponse findScoreBySbd(String sbd);

    List<TopStudentScoreResponse> findTopScoreGroupA();
}
