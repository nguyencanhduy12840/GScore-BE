package com.gscore.gscore.repository;

import com.gscore.gscore.entity.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScoreRepository extends JpaRepository<ScoreEntity, Long> {
    List<ScoreEntity> findByStudent_StudentId(String studentId);

    List<ScoreEntity> findBySubject_Code(String subjectCode);

    @Query(value = """
    SELECT s.subject_code,
           SUM(CASE WHEN s.score >= 8 THEN 1 ELSE 0 END) AS excellent,
           SUM(CASE WHEN s.score >= 6 AND s.score < 8 THEN 1 ELSE 0 END) AS good,
           SUM(CASE WHEN s.score >= 4 AND s.score < 6 THEN 1 ELSE 0 END) AS average,
           SUM(CASE WHEN s.score < 4 THEN 1 ELSE 0 END) AS poor
    FROM score s
    GROUP BY s.subject_code
""", nativeQuery = true)
    List<Object[]> getScoreStatisticsBySubject();
}
