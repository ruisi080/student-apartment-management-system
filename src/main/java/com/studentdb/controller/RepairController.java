package com.studentdb.controller;

import com.studentdb.entity.RepairOrder;
import com.studentdb.service.RepairService;
import com.studentdb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/repairs")
@CrossOrigin(origins = "*")
public class RepairController {
    private final RepairService repairService;
    private final UserService userService;
    public RepairController(RepairService repairService, UserService userService) { this.repairService = repairService; this.userService = userService; }
    @PostMapping
    public ResponseEntity<RepairOrder> create(@RequestBody RepairOrder o) { return ResponseEntity.status(HttpStatus.CREATED).body(repairService.create(o)); }
    @GetMapping
    public ResponseEntity<List<RepairOrder>> findAll(@RequestParam(required = false) String status) {
        if (status != null) return ResponseEntity.ok(repairService.findByStatus(RepairOrder.Status.valueOf(status)));
        return ResponseEntity.ok(repairService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<RepairOrder> findById(@PathVariable Long id) { return ResponseEntity.ok(repairService.findById(id)); }
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<RepairOrder>> findByStudent(@PathVariable Long studentId) { return ResponseEntity.ok(repairService.findByStudentId(studentId)); }
    @GetMapping("/maintenance/{userId}")
    public ResponseEntity<List<RepairOrder>> findByMaintenance(@PathVariable Long userId) { return ResponseEntity.ok(repairService.findByMaintenanceUserId(userId)); }
    @PutMapping("/{id}/assign")
    public ResponseEntity<RepairOrder> assign(@PathVariable Long id, @RequestBody Map<String, Long> body) { return ResponseEntity.ok(repairService.assign(id, body.get("maintenanceUserId"), userService)); }
    @PutMapping("/{id}/complete")
    public ResponseEntity<RepairOrder> complete(@PathVariable Long id, @RequestBody Map<String, String> body) { return ResponseEntity.ok(repairService.complete(id, body.get("repairNote"))); }
}
