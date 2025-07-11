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
public class StatisticsResponse {

    private List<Statistics> statisticsList;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Statistics {
        private String subject;
        private Long excellent;
        private Long good;
        private Long average;
        private Long weak;
    }
}
