package kr.myStudent.progress.domain;

import jakarta.persistence.*;
import kr.myStudent.enums.Understanding;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "ms_progress")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long progressId;

    @Column(nullable = false)
    private Long textbookId;

    @Column(nullable = false)
    private Long studentId;

    @Column(nullable = false)
    private String userId;

    private Long scheduleId; // 연관된 일정 ID

    @Column(nullable = false)
    private Integer lessonCount;

    @Column(nullable = false)
    private LocalDateTime lessonDate;

    @Column(nullable = false)
    private String unit;

    private Integer pageStart;

    private Integer pageEnd;

    @Enumerated(EnumType.STRING)
    private Understanding understanding;

    @Column(columnDefinition = "TEXT")
    private String memo;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public void updateProgress(LocalDateTime lessonDate, String unit, Integer pageStart, Integer pageEnd,
            Understanding understanding, String memo) {
        this.lessonDate = lessonDate;
        this.unit = unit;
        this.pageStart = pageStart;
        this.pageEnd = pageEnd;
        this.understanding = understanding;
        this.memo = memo;
    }
}
