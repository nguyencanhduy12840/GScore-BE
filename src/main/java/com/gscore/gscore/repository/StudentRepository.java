package com.gscore.gscore.repository;

import com.gscore.gscore.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface StudentRepository extends JpaRepository<StudentEntity, String> {
    Optional<StudentEntity> findByStudentId(String studentId);

    @Query("select st.studentId, st.foreignId, sum(sc.score) from StudentEntity st " +
            "inner join ScoreEntity sc on st.studentId = sc.student.studentId " +
            "where sc.subject.code in ('toan', 'vat_li', 'hoa_hoc')" +
            "group by st.studentId having count(sc) = 3 order by sum(sc.score) desc limit 10")
    List<Object[]> findTopStudent();
}
