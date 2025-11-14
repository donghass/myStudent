package kr.myStudent.domain;

import jakarta.persistence.*;
import kr.myStudent.domain.enums.Subject;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ms_schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long scheduleId;      // 스케쥴아이디 (PK)

    @Enumerated(EnumType.STRING)
    @Column(name = "subject")
    private Subject subject;      // 과목 ENUM

    @Column(name = "student_id", length = 20, nullable = false)
    private String studentId;     // 학생아이디

    @Column(name = "user_id", length = 20, nullable = false)
    private String userId;        // 선생아이디

    @Column(name = "class_date")
    private LocalDateTime classDate;  // 수업일

    @Column(name = "class_location", length = 50)
    private String classLocation;     // 수업장소

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;           // 수업내용
}
