package com.gscore.gscore.utils;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Component
@RequiredArgsConstructor
public class CsvSeeder {

    private final JdbcTemplate jdbcTemplate;

    private static final int BATCH_SIZE = 10_000;
    private static final String CSV_PATH = "src/main/resources/diem_thi_thpt_2024.csv";

    private static final String INSERT_SUBJECT_SQL =
            "INSERT IGNORE INTO subject (code, name) VALUES (?, ?)";
    private static final String INSERT_STUDENT_SQL =
            "INSERT IGNORE INTO student (student_id, foreign_id) VALUES (?, ?)";
    private static final String INSERT_SCORE_SQL =
            "INSERT INTO score (student_id, subject_code, score) VALUES (?, ?, ?)";

    @PostConstruct
    public void seed() throws Exception {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM student", Integer.class);
        if (count != null && count > 0) {
            System.out.printf("⚠️ %d students already exist in the database, skipping seeding.%n", count);
            return;
        }

        long start = System.currentTimeMillis();
        System.out.println("🚀 Starting data seeding using JdbcTemplate...");

        // 1. Seed subjects if not already present
        jdbcTemplate.batchUpdate(INSERT_SUBJECT_SQL, List.of(
                new Object[]{"toan", "Math"},
                new Object[]{"ngu_van", "Literature"},
                new Object[]{"ngoai_ngu", "Foreign Language"},
                new Object[]{"vat_li", "Physics"},
                new Object[]{"hoa_hoc", "Chemistry"},
                new Object[]{"sinh_hoc", "Biology"},
                new Object[]{"lich_su", "History"},
                new Object[]{"dia_li", "Geography"},
                new Object[]{"gdcd", "Civic Education"}
        ));

        // 2. Read CSV
        Path path = Paths.get(CSV_PATH);
        try (BufferedReader br = Files.newBufferedReader(path);
             CSVParser parser = new CSVParser(br, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            List<Object[]> studentParams = new ArrayList<>();
            List<Object[]> scoreParams = new ArrayList<>();

            int totalStudents = 0;
            int totalScores = 0;

            for (CSVRecord row : parser) {
                String sbd = row.get("sbd");
                String maNgoaiNgu = row.get("ma_ngoai_ngu");

                studentParams.add(new Object[]{sbd, maNgoaiNgu});
                totalStudents++;

                for (String subjectCode : row.toMap().keySet()) {
                    if (subjectCode.equals("sbd") || subjectCode.equals("ma_ngoai_ngu")) continue;

                    String scoreStr = row.get(subjectCode);
                    if (scoreStr == null || scoreStr.isBlank()) continue;

                    try {
                        double scoreVal = Double.parseDouble(scoreStr);
                        scoreParams.add(new Object[]{sbd, subjectCode, scoreVal});
                        totalScores++;
                    } catch (NumberFormatException e) {
                        System.err.printf("⚠️ Invalid score: sbd=%s, subject=%s, score='%s'%n",
                                sbd, subjectCode, scoreStr);
                    }
                }

                if (studentParams.size() >= BATCH_SIZE) {
                    jdbcTemplate.batchUpdate(INSERT_STUDENT_SQL, studentParams);
                    jdbcTemplate.batchUpdate(INSERT_SCORE_SQL, scoreParams);
                    System.out.printf("✅ Inserted %d students, %d scores%n", totalStudents, totalScores);
                    studentParams.clear();
                    scoreParams.clear();
                }
            }

            // Insert remaining data
            if (!studentParams.isEmpty()) {
                jdbcTemplate.batchUpdate(INSERT_STUDENT_SQL, studentParams);
                jdbcTemplate.batchUpdate(INSERT_SCORE_SQL, scoreParams);
                System.out.printf("✅ Inserted remaining: %d students, %d scores%n",
                        studentParams.size(), scoreParams.size());
            }

            long end = System.currentTimeMillis();
            System.out.printf("🎯 Total: %d students, %d scores%n", totalStudents, totalScores);
            System.out.printf("🎉 Seeding completed. Total time: %.2f seconds%n", (end - start) / 1000.0);
        }
    }
}
