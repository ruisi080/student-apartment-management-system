package com.studentdb.service;

import com.studentdb.entity.Student;
import com.studentdb.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        if (studentRepository.existsByStudentNo(student.getStudentNo())) {
            throw new RuntimeException("学号已存在: " + student.getStudentNo());
        }
        return studentRepository.save(student);
    }

    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Student> getStudentByStudentNo(String studentNo) {
        return studentRepository.findByStudentNo(studentNo);
    }

    @Transactional(readOnly = true)
    public List<Student> searchStudentsByName(String name) {
        return studentRepository.findByNameContaining(name);
    }

    @Transactional(readOnly = true)
    public List<Student> getStudentsByMajor(String major) {
        return studentRepository.findByMajor(major);
    }

    @Transactional(readOnly = true)
    public List<Student> getStudentsByClass(String className) {
        return studentRepository.findByClassName(className);
    }

    @Transactional(readOnly = true)
    public List<Student> getStudentsByGender(String gender) {
        return studentRepository.findByGender(gender);
    }

    @Transactional(readOnly = true)
    public List<Student> getStudentsByEnrollmentYear(Integer year) {
        return studentRepository.findByEnrollmentYear(year);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("学生不存在，ID: " + id));

        // 检查学号是否已被其他学生使用
        if (!student.getStudentNo().equals(studentDetails.getStudentNo())
                && studentRepository.existsByStudentNo(studentDetails.getStudentNo())) {
            throw new RuntimeException("学号已存在: " + studentDetails.getStudentNo());
        }

        student.setStudentNo(studentDetails.getStudentNo());
        student.setName(studentDetails.getName());
        student.setAge(studentDetails.getAge());
        student.setGender(studentDetails.getGender());
        student.setBirthDate(studentDetails.getBirthDate());
        student.setEmail(studentDetails.getEmail());
        student.setPhone(studentDetails.getPhone());
        student.setAddress(studentDetails.getAddress());
        student.setEnrollmentYear(studentDetails.getEnrollmentYear());
        student.setMajor(studentDetails.getMajor());
        student.setClassName(studentDetails.getClassName());

        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("学生不存在，ID: " + id);
        }
        studentRepository.deleteById(id);
    }

    public long countStudents() {
        return studentRepository.count();
    }
}
