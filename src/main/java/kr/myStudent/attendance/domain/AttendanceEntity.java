package kr.myStudent.attendance.domain;

import jakarta.persistence.*;
import kr.myStudent.enums.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "ms_attendance")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long scheduleId;

    @Column(nullable = false, length = 20)
    private Long studentId;

    @Column(nullable = false)
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AttendanceStatus status;

    private LocalDateTime checkInTime;

    private LocalDateTime checkOutTime;

    @Column(length = 500)
    private String memo;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public void update(AttendanceStatus status, LocalDateTime checkInTime, LocalDateTime checkOutTime, String memo) {
        this.status = status;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.memo = memo;
    }
}
