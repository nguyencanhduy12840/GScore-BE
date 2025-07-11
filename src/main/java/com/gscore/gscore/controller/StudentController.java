package com.gscore.gscore.controller;

import com.gscore.gscore.dto.StudentScoreResponse;
import com.gscore.gscore.dto.TopStudentScoreResponse;
import com.gscore.gscore.service.IStudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
@Tag(name = "Student API", description = "Endpoints for managing students")
public class StudentController {

    private final IStudentService studentService;

    @GetMapping("/{sbd}")
    public ResponseEntity<?> findStudent(@PathVariable("sbd") String sbd) {
        StudentScoreResponse studentScoreResponse = studentService.findScoreBySbd(sbd);
        return ResponseEntity.ok(studentScoreResponse);
    }

    @GetMapping("/top10-GroupA")
    public ResponseEntity<?> findTop10Students() {
        List<TopStudentScoreResponse> result = studentService.findTopScoreGroupA();
        return ResponseEntity.ok(result);
    }
}
