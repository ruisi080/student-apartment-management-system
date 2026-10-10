package com.studentdb.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "repair_order")
public class RepairOrder {

    public enum Urgency { LOW, MEDIUM, HIGH, EMERGENCY }
    public enum Status { PENDING, ASSIGNED, IN_PROGRESS, COMPLETED }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @NotBlank(message = "故障描述不能为空")
    @Column(nullable = false, length = 1000)
    private String description;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Urgency urgency = Urgency.MEDIUM;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status = Status.PENDING;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "maintenance_user_id")
    private User maintenanceUser;

    @Column(name = "repair_note", length = 500)
    private String repairNote;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    public RepairOrder() {}

    @PrePersist
    protected void onCreate() { createdAt = LocalDateTime.now(); }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public Urgency getUrgency() { return urgency; }
    public void setUrgency(Urgency urgency) { this.urgency = urgency; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public User getMaintenanceUser() { return maintenanceUser; }
    public void setMaintenanceUser(User maintenanceUser) { this.maintenanceUser = maintenanceUser; }
    public String getRepairNote() { return repairNote; }
    public void setRepairNote(String repairNote) { this.repairNote = repairNote; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
    public Long getStudentId() { return student != null ? student.getId() : null; }
    public String getStudentName() { return student != null ? student.getName() : null; }
    public Long getRoomId() { return room != null ? room.getId() : null; }
    public String getRoomNo() { return room != null ? room.getRoomNo() : null; }
    public Long getMaintenanceUserId() { return maintenanceUser != null ? maintenanceUser.getId() : null; }
    public String getMaintenanceUserName() { return maintenanceUser != null ? maintenanceUser.getName() : null; }
}
