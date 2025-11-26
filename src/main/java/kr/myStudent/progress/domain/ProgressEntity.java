package kr.myStudent.progress.domain;

import jakarta.persistence.*;
import kr.myStudent.enums.Subject;
import lombok.*;

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
    private Long studentId;

    @Column(nullable = false)
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Subject subject;

    @Column(nullable = false)
    private LocalDateTime lessonDate;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(columnDefinition = "TEXT")
    private String homework;

    @Column(columnDefinition = "TEXT")
    private String memo;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

    public void updateProgress(Subject subject, LocalDateTime lessonDate, String content, String homework, String memo) {
        this.subject = subject;
        this.lessonDate = lessonDate;
        this.content = content;
        this.homework = homework;
        this.memo = memo;
        this.updatedAt = LocalDateTime.now();
    }
}
