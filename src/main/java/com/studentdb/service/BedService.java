package com.studentdb.service;

import com.studentdb.entity.Bed;
import com.studentdb.repository.BedRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class BedService {
    private final BedRepository bedRepository;
    public BedService(BedRepository bedRepository) { this.bedRepository = bedRepository; }
    public Bed create(Bed b) { return bedRepository.save(b); }
    @Transactional(readOnly = true)
    public List<Bed> findAll() { return bedRepository.findAll(); }
    @Transactional(readOnly = true)
    public Bed findById(Long id) { return bedRepository.findById(id).orElseThrow(() -> new RuntimeException("床位不存在: " + id)); }
    @Transactional(readOnly = true)
    public List<Bed> findByRoomId(Long roomId) { return bedRepository.findByRoom_Id(roomId); }
    @Transactional(readOnly = true)
    public List<Bed> findFreeByRoomId(Long roomId) { return bedRepository.findByRoom_IdAndStatus(roomId, Bed.Status.FREE); }
    public Bed update(Long id, Bed data) {
        Bed b = findById(id);
        b.setBedNo(data.getBedNo());
        b.setStatus(data.getStatus());
        return bedRepository.save(b);
    }
    public void delete(Long id) { bedRepository.deleteById(id); }
}
