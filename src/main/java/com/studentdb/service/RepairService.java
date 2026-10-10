package com.studentdb.service;

import com.studentdb.entity.*;
import com.studentdb.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class RepairService {
    private final RepairOrderRepository repairRepo;
    public RepairService(RepairOrderRepository repairRepo) { this.repairRepo = repairRepo; }
    public RepairOrder create(RepairOrder order) { return repairRepo.save(order); }
    @Transactional(readOnly = true)
    public List<RepairOrder> findAll() { return repairRepo.findAll(); }
    @Transactional(readOnly = true)
    public RepairOrder findById(Long id) { return repairRepo.findById(id).orElseThrow(() -> new RuntimeException("工单不存在: " + id)); }
    @Transactional(readOnly = true)
    public List<RepairOrder> findByStudentId(Long studentId) { return repairRepo.findByStudent_Id(studentId); }
    @Transactional(readOnly = true)
    public List<RepairOrder> findByStatus(RepairOrder.Status status) { return repairRepo.findByStatus(status); }
    @Transactional(readOnly = true)
    public List<RepairOrder> findByMaintenanceUserId(Long userId) { return repairRepo.findByMaintenanceUser_Id(userId); }
    public RepairOrder assign(Long id, Long maintenanceUserId, UserService userService) {
        RepairOrder order = findById(id);
        order.setMaintenanceUser(userService.findById(maintenanceUserId));
        order.setStatus(RepairOrder.Status.ASSIGNED);
        return repairRepo.save(order);
    }
    public RepairOrder complete(Long id, String repairNote) {
        RepairOrder order = findById(id);
        order.setStatus(RepairOrder.Status.COMPLETED);
        order.setRepairNote(repairNote);
        order.setCompletedAt(LocalDateTime.now());
        return repairRepo.save(order);
    }
}
