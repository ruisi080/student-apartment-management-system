package com.studentdb.controller;

import com.studentdb.entity.Announcement;
import com.studentdb.service.AnnouncementService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/announcements")
@CrossOrigin(origins = "*")
public class AnnouncementController {
    private final AnnouncementService announcementService;
    public AnnouncementController(AnnouncementService announcementService) { this.announcementService = announcementService; }
    @PostMapping
    public ResponseEntity<Announcement> create(@RequestBody Announcement a) { return ResponseEntity.status(HttpStatus.CREATED).body(announcementService.create(a)); }
    @GetMapping
    public ResponseEntity<List<Announcement>> findAll() { return ResponseEntity.ok(announcementService.findAll()); }
    @GetMapping("/{id}")
    public ResponseEntity<Announcement> findById(@PathVariable Long id) { return ResponseEntity.ok(announcementService.findById(id)); }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { announcementService.delete(id); return ResponseEntity.noContent().build(); }
}
