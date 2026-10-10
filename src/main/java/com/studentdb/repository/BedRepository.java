package com.studentdb.repository;

import com.studentdb.entity.Bed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BedRepository extends JpaRepository<Bed, Long> {
    List<Bed> findByRoom_Id(Long roomId);
    List<Bed> findByRoom_IdAndStatus(Long roomId, Bed.Status status);
    List<Bed> findByStatus(Bed.Status status);
    boolean existsByBedNoAndRoom_Id(String bedNo, Long roomId);
}
