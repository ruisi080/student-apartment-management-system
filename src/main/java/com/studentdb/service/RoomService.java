package com.studentdb.service;

import com.studentdb.entity.Room;
import com.studentdb.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class RoomService {
    private final RoomRepository roomRepository;
    public RoomService(RoomRepository roomRepository) { this.roomRepository = roomRepository; }
    public Room create(Room r) { return roomRepository.save(r); }
    @Transactional(readOnly = true)
    public List<Room> findAll() { return roomRepository.findAll(); }
    @Transactional(readOnly = true)
    public Room findById(Long id) { return roomRepository.findById(id).orElseThrow(() -> new RuntimeException("房间不存在: " + id)); }
    @Transactional(readOnly = true)
    public List<Room> findByBuildingId(Long buildingId) { return roomRepository.findByBuilding_Id(buildingId); }
    @Transactional(readOnly = true)
    public List<Room> findFree() { return roomRepository.findByStatus(Room.Status.FREE); }
    public Room update(Long id, Room data) {
        Room r = findById(id);
        r.setRoomNo(data.getRoomNo());
        r.setFloorNumber(data.getFloorNumber());
        r.setCapacity(data.getCapacity());
        r.setStatus(data.getStatus());
        r.setPrice(data.getPrice());
        r.setDescription(data.getDescription());
        return roomRepository.save(r);
    }
    public void delete(Long id) { roomRepository.deleteById(id); }
}
