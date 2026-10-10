package com.studentdb.controller;

import com.studentdb.entity.Building;
import com.studentdb.service.BuildingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/buildings")
@CrossOrigin(origins = "*")
public class BuildingController {
    private final BuildingService buildingService;
    public BuildingController(BuildingService buildingService) { this.buildingService = buildingService; }
    @PostMapping
    public ResponseEntity<Building> create(@RequestBody Building b) { return ResponseEntity.status(HttpStatus.CREATED).body(buildingService.create(b)); }
    @GetMapping
    public ResponseEntity<List<Building>> findAll() { return ResponseEntity.ok(buildingService.findAll()); }
    @GetMapping("/{id}")
    public ResponseEntity<Building> findById(@PathVariable Long id) { return ResponseEntity.ok(buildingService.findById(id)); }
    @PutMapping("/{id}")
    public ResponseEntity<Building> update(@PathVariable Long id, @RequestBody Building b) { return ResponseEntity.ok(buildingService.update(id, b)); }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { buildingService.delete(id); return ResponseEntity.noContent().build(); }
}
