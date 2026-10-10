package com.studentdb.controller;

import com.studentdb.entity.Student;
import com.studentdb.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.createStudent(student));
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/no/{studentNo}")
    public ResponseEntity<Student> getStudentByStudentNo(@PathVariable String studentNo) {
        return studentService.getStudentByStudentNo(studentNo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchStudents(@RequestParam(required = false) String name,
                                                         @RequestParam(required = false) String major,
                                                         @RequestParam(required = false) String className,
                                                         @RequestParam(required = false) String gender,
                                                         @RequestParam(required = false) Integer enrollmentYear) {
        if (name != null) {
            return ResponseEntity.ok(studentService.searchStudentsByName(name));
        }
        if (major != null) {
            return ResponseEntity.ok(studentService.getStudentsByMajor(major));
        }
        if (className != null) {
            return ResponseEntity.ok(studentService.getStudentsByClass(className));
        }
        if (gender != null) {
            return ResponseEntity.ok(studentService.getStudentsByGender(gender));
        }
        if (enrollmentYear != null) {
            return ResponseEntity.ok(studentService.getStudentsByEnrollmentYear(enrollmentYear));
        }
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id,
                                                  @Valid @RequestBody Student student) {
        return ResponseEntity.ok(studentService.updateStudent(id, student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        return ResponseEntity.ok(Map.of(
                "totalStudents", studentService.countStudents(),
                "message", "学生管理系统运行中"
        ));
    }
}
