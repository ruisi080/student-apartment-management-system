package com.studentdb.service;

import com.studentdb.entity.*;
import com.studentdb.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class CheckInService {
    private final CheckInRecordRepository checkInRepo;
    private final StudentRepository studentRepo;
    private final BedRepository bedRepo;
    public CheckInService(CheckInRecordRepository checkInRepo, StudentRepository studentRepo, BedRepository bedRepo) {
        this.checkInRepo = checkInRepo; this.studentRepo = studentRepo; this.bedRepo = bedRepo;
    }
    public CheckInRecord checkIn(Long studentId, Long bedId) {
        Student student = studentRepo.findById(studentId).orElseThrow(() -> new RuntimeException("学生不存在"));
        Bed bed = bedRepo.findById(bedId).orElseThrow(() -> new RuntimeException("床位不存在"));
        if (bed.getStatus() != Bed.Status.FREE) throw new RuntimeException("该床位已被占用");
        checkInRepo.findByStudent_IdAndStatus(studentId, CheckInRecord.Status.ACTIVE).ifPresent(r -> {
            throw new RuntimeException("该学生已入住，请先退宿");
        });
        bed.setStatus(Bed.Status.OCCUPIED);
        bedRepo.save(bed);
        Room room = bed.getRoom();
        room.setCurrentOccupancy(room.getCurrentOccupancy() + 1);
        CheckInRecord record = new CheckInRecord();
        record.setStudent(student);
        record.setBed(bed);
        record.setCheckInDate(LocalDate.now());
        record.setStatus(CheckInRecord.Status.ACTIVE);
        return checkInRepo.save(record);
    }
    public CheckInRecord checkOut(Long recordId) {
        CheckInRecord record = checkInRepo.findById(recordId).orElseThrow(() -> new RuntimeException("入住记录不存在"));
        record.setStatus(CheckInRecord.Status.CHECKED_OUT);
        record.setCheckOutDate(LocalDate.now());
        Bed bed = record.getBed();
        bed.setStatus(Bed.Status.FREE);
        bedRepo.save(bed);
        Room room = bed.getRoom();
        room.setCurrentOccupancy(Math.max(0, room.getCurrentOccupancy() - 1));
        return checkInRepo.save(record);
    }
    @Transactional(readOnly = true)
    public List<CheckInRecord> findAll() { return checkInRepo.findAll(); }
    @Transactional(readOnly = true)
    public List<CheckInRecord> findByStudentId(Long studentId) { return checkInRepo.findByStudent_Id(studentId); }
    @Transactional(readOnly = true)
    public List<CheckInRecord> findActive() { return checkInRepo.findByStatus(CheckInRecord.Status.ACTIVE); }
}
