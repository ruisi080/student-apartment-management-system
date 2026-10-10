package com.studentdb.repository;

import com.studentdb.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByStudentNo(String studentNo);

    List<Student> findByNameContaining(String name);

    List<Student> findByMajor(String major);

    List<Student> findByClassName(String className);

    List<Student> findByGender(String gender);

    List<Student> findByEnrollmentYear(Integer enrollmentYear);

    boolean existsByStudentNo(String studentNo);
}
