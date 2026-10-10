package com.studentdb.controller;

import com.studentdb.entity.CheckInRecord;
import com.studentdb.service.CheckInService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/checkin")
@CrossOrigin(origins = "*")
public class CheckInController {
    private final CheckInService checkInService;
    public CheckInController(CheckInService checkInService) { this.checkInService = checkInService; }
    @PostMapping
    public ResponseEntity<CheckInRecord> checkIn(@RequestBody Map<String, Long> body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(checkInService.checkIn(body.get("studentId"), body.get("bedId")));
    }
    @PostMapping("/{id}/checkout")
    public ResponseEntity<CheckInRecord> checkOut(@PathVariable Long id) { return ResponseEntity.ok(checkInService.checkOut(id)); }
    @GetMapping
    public ResponseEntity<List<CheckInRecord>> findAll() { return ResponseEntity.ok(checkInService.findAll()); }
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<CheckInRecord>> findByStudent(@PathVariable Long studentId) { return ResponseEntity.ok(checkInService.findByStudentId(studentId)); }
    @GetMapping("/active")
    public ResponseEntity<List<CheckInRecord>> findActive() { return ResponseEntity.ok(checkInService.findActive()); }
}
