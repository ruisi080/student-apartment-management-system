package com.studentdb.controller;

import com.studentdb.entity.Room;
import com.studentdb.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "*")
public class RoomController {
    private final RoomService roomService;
    public RoomController(RoomService roomService) { this.roomService = roomService; }
    @PostMapping
    public ResponseEntity<Room> create(@RequestBody Room r) { return ResponseEntity.status(HttpStatus.CREATED).body(roomService.create(r)); }
    @GetMapping
    public ResponseEntity<List<Room>> findAll(@RequestParam(required = false) Long buildingId, @RequestParam(required = false) String status) {
        if (buildingId != null) return ResponseEntity.ok(roomService.findByBuildingId(buildingId));
        if ("FREE".equals(status)) return ResponseEntity.ok(roomService.findFree());
        return ResponseEntity.ok(roomService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Room> findById(@PathVariable Long id) { return ResponseEntity.ok(roomService.findById(id)); }
    @PutMapping("/{id}")
    public ResponseEntity<Room> update(@PathVariable Long id, @RequestBody Room r) { return ResponseEntity.ok(roomService.update(id, r)); }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { roomService.delete(id); return ResponseEntity.noContent().build(); }
}
