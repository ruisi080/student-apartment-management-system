package com.studentdb.service;

import com.studentdb.entity.Building;
import com.studentdb.repository.BuildingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class BuildingService {
    private final BuildingRepository buildingRepository;
    public BuildingService(BuildingRepository buildingRepository) { this.buildingRepository = buildingRepository; }
    public Building create(Building b) { return buildingRepository.save(b); }
    @Transactional(readOnly = true)
    public List<Building> findAll() { return buildingRepository.findAll(); }
    @Transactional(readOnly = true)
    public Building findById(Long id) { return buildingRepository.findById(id).orElseThrow(() -> new RuntimeException("楼栋不存在: " + id)); }
    public Building update(Long id, Building data) {
        Building b = findById(id);
        b.setName(data.getName());
        b.setLocation(data.getLocation());
        b.setFloorCount(data.getFloorCount());
        return buildingRepository.save(b);
    }
    public void delete(Long id) { buildingRepository.deleteById(id); }
}
