package com.studentdb.service;

import com.studentdb.entity.UtilityBill;
import com.studentdb.repository.UtilityBillRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class UtilityBillService {
    private final UtilityBillRepository billRepo;
    public UtilityBillService(UtilityBillRepository billRepo) { this.billRepo = billRepo; }
    public UtilityBill create(UtilityBill bill) { return billRepo.save(bill); }
    @Transactional(readOnly = true)
    public List<UtilityBill> findAll() { return billRepo.findAll(); }
    @Transactional(readOnly = true)
    public List<UtilityBill> findByRoomId(Long roomId) { return billRepo.findByRoom_Id(roomId); }
    @Transactional(readOnly = true)
    public List<UtilityBill> findByRoomAndMonth(Long roomId, Integer year, Integer month) { return billRepo.findByRoom_IdAndYearAndMonth(roomId, year, month); }
    public UtilityBill pay(Long id) {
        UtilityBill bill = billRepo.findById(id).orElseThrow(() -> new RuntimeException("账单不存在: " + id));
        bill.setIsPaid(true);
        bill.setPaidAt(java.time.LocalDateTime.now());
        return billRepo.save(bill);
    }
    public void delete(Long id) { billRepo.deleteById(id); }
}
