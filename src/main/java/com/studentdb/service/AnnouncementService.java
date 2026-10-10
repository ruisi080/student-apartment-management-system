package com.studentdb.service;

import com.studentdb.entity.Announcement;
import com.studentdb.repository.AnnouncementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class AnnouncementService {
    private final AnnouncementRepository announcementRepo;
    public AnnouncementService(AnnouncementRepository announcementRepo) { this.announcementRepo = announcementRepo; }
    public Announcement create(Announcement a) { return announcementRepo.save(a); }
    @Transactional(readOnly = true)
    public List<Announcement> findAll() { return announcementRepo.findAllByOrderByPublishedAtDesc(); }
    @Transactional(readOnly = true)
    public Announcement findById(Long id) { return announcementRepo.findById(id).orElseThrow(() -> new RuntimeException("公告不存在: " + id)); }
    public void delete(Long id) { announcementRepo.deleteById(id); }
}
