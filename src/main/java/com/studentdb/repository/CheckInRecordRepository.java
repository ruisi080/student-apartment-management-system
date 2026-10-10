package com.studentdb.repository;

import com.studentdb.entity.CheckInRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CheckInRecordRepository extends JpaRepository<CheckInRecord, Long> {
    List<CheckInRecord> findByStudent_Id(Long studentId);
    List<CheckInRecord> findByBed_Id(Long bedId);
    Optional<CheckInRecord> findByStudent_IdAndStatus(Long studentId, CheckInRecord.Status status);
    List<CheckInRecord> findByStatus(CheckInRecord.Status status);
}
