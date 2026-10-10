package com.studentdb.repository;

import com.studentdb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByBuilding_Id(Long buildingId);
    List<Room> findByStatus(Room.Status status);
    List<Room> findByBuilding_IdAndStatus(Long buildingId, Room.Status status);
    boolean existsByRoomNoAndBuilding_Id(String roomNo, Long buildingId);
}
