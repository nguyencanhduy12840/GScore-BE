package com.gscore.gscore.service.impl;

import com.gscore.gscore.dto.StudentScoreResponse;
import com.gscore.gscore.dto.SubjectScoreDTO;
import com.gscore.gscore.dto.TopStudentScoreResponse;
import com.gscore.gscore.entity.ScoreEntity;
import com.gscore.gscore.entity.StudentEntity;
import com.gscore.gscore.exception.NotFoundException;
import com.gscore.gscore.repository.ScoreRepository;
import com.gscore.gscore.repository.StudentRepository;
import com.gscore.gscore.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {

    private final StudentRepository studentRepository;

    private final ScoreRepository scoreRepository;

    @Override
    public StudentScoreResponse findScoreBySbd(String sbd) {
        Optional<StudentEntity> student = studentRepository.findByStudentId(sbd);
        if(student.isPresent()) {
            List<ScoreEntity> scoreList = scoreRepository.findByStudent_StudentId(student.get().getStudentId());
            if(scoreList.isEmpty()) {
                throw new NotFoundException("Score of student with studentId " + sbd + " not found");
            }
            StudentScoreResponse result = new StudentScoreResponse();
            result.setStudentId(student.get().getStudentId());
            result.setForeignId(student.get().getForeignId());
            List<SubjectScoreDTO> scores = new ArrayList<>();
            for(ScoreEntity scoreEntity : scoreList) {
                SubjectScoreDTO sc = new SubjectScoreDTO();
                sc.setCode(scoreEntity.getSubject().getCode());
                sc.setScore(scoreEntity.getScore());
                scores.add(sc);
            }
            result.setScores(scores);
            return result;
        }
        else{
            throw new NotFoundException("Student with studentId " + sbd + " not found");
        }
    }

    @Override
    public List<TopStudentScoreResponse> findTopScoreGroupA() {
        List<Object[]> students = studentRepository.findTopStudent();
        List<TopStudentScoreResponse> result = new ArrayList<>();
        for(Object[] student: students) {
            TopStudentScoreResponse response = new TopStudentScoreResponse();
            String studentId = (String) student[0];
            String foreignId = (String) student[1];
            Double score = (Double) student[2];
            response.setStudentId(studentId);
            response.setForeignId(foreignId);
            response.setTotalScore(score);
            List<ScoreEntity> scoreList = scoreRepository.findByStudent_StudentId(studentId);
            List<SubjectScoreDTO> scores = scoreList.stream().filter(item -> {
                String code = item.getSubject().getCode();
                return code.equals("toan") || code.equals("vat_li") || code.equals("hoa_hoc");
            }).map(item -> new SubjectScoreDTO(item.getSubject().getCode(), item.getScore())).toList();
            response.setScores(scores);
            result.add(response);
        }
        return result;
    }
}
