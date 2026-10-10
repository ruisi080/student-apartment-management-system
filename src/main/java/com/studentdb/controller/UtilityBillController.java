package com.studentdb.controller;

import com.studentdb.entity.UtilityBill;
import com.studentdb.service.UtilityBillService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bills")
@CrossOrigin(origins = "*")
public class UtilityBillController {
    private final UtilityBillService billService;
    public UtilityBillController(UtilityBillService billService) { this.billService = billService; }
    @PostMapping
    public ResponseEntity<UtilityBill> create(@RequestBody UtilityBill bill) { return ResponseEntity.status(HttpStatus.CREATED).body(billService.create(bill)); }
    @GetMapping
    public ResponseEntity<List<UtilityBill>> findAll(@RequestParam(required = false) Long roomId) {
        if (roomId != null) return ResponseEntity.ok(billService.findByRoomId(roomId));
        return ResponseEntity.ok(billService.findAll());
    }
    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<UtilityBill>> findByRoom(@PathVariable Long roomId, @RequestParam Integer year, @RequestParam Integer month) { return ResponseEntity.ok(billService.findByRoomAndMonth(roomId, year, month)); }
    @PutMapping("/{id}/pay")
    public ResponseEntity<UtilityBill> pay(@PathVariable Long id) { return ResponseEntity.ok(billService.pay(id)); }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { billService.delete(id); return ResponseEntity.noContent().build(); }
}
