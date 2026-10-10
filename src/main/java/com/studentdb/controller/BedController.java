package com.studentdb.controller;

import com.studentdb.entity.Bed;
import com.studentdb.service.BedService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/beds")
@CrossOrigin(origins = "*")
public class BedController {
    private final BedService bedService;
    public BedController(BedService bedService) { this.bedService = bedService; }
    @PostMapping
    public ResponseEntity<Bed> create(@RequestBody Bed b) { return ResponseEntity.status(HttpStatus.CREATED).body(bedService.create(b)); }
    @GetMapping
    public ResponseEntity<List<Bed>> findAll(@RequestParam(required = false) Long roomId, @RequestParam(required = false) Boolean free) {
        if (roomId != null && Boolean.TRUE.equals(free)) return ResponseEntity.ok(bedService.findFreeByRoomId(roomId));
        if (roomId != null) return ResponseEntity.ok(bedService.findByRoomId(roomId));
        return ResponseEntity.ok(bedService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Bed> findById(@PathVariable Long id) { return ResponseEntity.ok(bedService.findById(id)); }
    @PutMapping("/{id}")
    public ResponseEntity<Bed> update(@PathVariable Long id, @RequestBody Bed b) { return ResponseEntity.ok(bedService.update(id, b)); }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { bedService.delete(id); return ResponseEntity.noContent().build(); }
}
