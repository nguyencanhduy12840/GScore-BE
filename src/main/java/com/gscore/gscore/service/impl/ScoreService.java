package com.gscore.gscore.service.impl;

import com.gscore.gscore.dto.StatisticsResponse;
import com.gscore.gscore.repository.ScoreRepository;
import com.gscore.gscore.service.IScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreService implements IScoreService {

    private final ScoreRepository scoreRepository;

    @Override
    public StatisticsResponse getStatistics() {
        List<Object[]> rawStats = scoreRepository.getScoreStatisticsBySubject();
        List<StatisticsResponse.Statistics> listResult = new ArrayList<>();

        for (Object[] row : rawStats) {
            String subjectCode = (String) row[0];
            Long excellent = ((BigDecimal) row[1]).longValue();
            Long good = ((BigDecimal) row[2]).longValue();
            Long average = ((BigDecimal) row[3]).longValue();
            Long weak = ((BigDecimal) row[4]).longValue();

            listResult.add(new StatisticsResponse.Statistics(subjectCode, excellent, good, average, weak));
        }

        StatisticsResponse response = new StatisticsResponse();
        response.setStatisticsList(listResult);
        return response;
    }
}
