package kr.myStudent.score.domain;


import jakarta.persistence.*;
import kr.myStudent.enums.Subject;
import kr.myStudent.score.dto.request.ScoreUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "ms_score")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_id")
    private Long scoreId;      // 점수아이디 (PK)

    @Column(name = "user_id", length = 20, nullable = false)
    private String userId;     // 선생아이디 (FK)

    @Column(name = "student_id", length = 20, nullable = false)
    private Long studentId;  // 학생아이디 (FK)

    @Enumerated(EnumType.STRING)
    @Column(name = "subject", nullable = false)
    private Subject subject;   // 과목 ENUM

    @Column(name = "score")
    private Double score;      // 점수

    @Column(name = "test_name", length = 20)
    private String testName;   // 시험명

    @Column(name = "test_date")
    private LocalDate testDate; // 시험날짜

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

    public void updateScore(ScoreUpdateRequest req) {
        this.subject = req.getSubject();
        this.score = req.getScore();
        this.testName = req.getTestName();
        this.testDate = req.getTestDate();
        this.updatedAt = LocalDateTime.now();
    }
}
