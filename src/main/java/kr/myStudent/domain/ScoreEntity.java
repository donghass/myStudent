package kr.myStudent.domain;

import jakarta.persistence.*;
import kr.myStudent.domain.enums.Subject;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "ms_score")
@Getter
@Setter
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
    private String studentId;  // 학생아이디 (FK)

    @Enumerated(EnumType.STRING)
    @Column(name = "subject", nullable = false)
    private Subject subject;   // 과목 ENUM

    @Column(name = "score")
    private Double score;      // 점수

    @Column(name = "test_name", length = 20)
    private String testName;   // 시험명

    @Column(name = "test_date")
    private LocalDate testDate; // 시험날짜


}
