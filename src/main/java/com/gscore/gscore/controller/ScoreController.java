package com.gscore.gscore.controller;

import com.gscore.gscore.service.IScoreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/scores")
@RequiredArgsConstructor
@Tag(name = "Score API", description = "Endpoints for managing scores")
public class ScoreController {

    private final IScoreService scoreService;

    @GetMapping
    public ResponseEntity<?> getStatistics(){
        return ResponseEntity.ok(scoreService.getStatistics());
    }
}
