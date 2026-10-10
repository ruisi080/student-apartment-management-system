package com.studentdb.repository;

import com.studentdb.entity.RepairOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RepairOrderRepository extends JpaRepository<RepairOrder, Long> {
    List<RepairOrder> findByStudent_Id(Long studentId);
    List<RepairOrder> findByRoom_Id(Long roomId);
    List<RepairOrder> findByStatus(RepairOrder.Status status);
    List<RepairOrder> findByMaintenanceUser_Id(Long userId);
    List<RepairOrder> findByUrgency(RepairOrder.Urgency urgency);
}
