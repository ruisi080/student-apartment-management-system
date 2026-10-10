package com.studentdb.repository;

import com.studentdb.entity.UtilityBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UtilityBillRepository extends JpaRepository<UtilityBill, Long> {
    List<UtilityBill> findByRoom_Id(Long roomId);
    List<UtilityBill> findByRoom_IdAndYearAndMonth(Long roomId, Integer year, Integer month);
    List<UtilityBill> findByIsPaid(Boolean isPaid);
}
