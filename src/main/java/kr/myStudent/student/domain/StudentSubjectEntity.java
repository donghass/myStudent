package kr.myStudent.student.domain;

import jakarta.persistence.*;
import kr.myStudent.enums.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "ms_student_subject")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentSubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentSubjectId;

    @Column(name = "student_id", nullable = false)
    private Long studentId; // FK(학생)

    @Column(name = "user_id", nullable = false)
    private String userId;      // 선생 아이디 (FK 역할)

    @Column(name = "subject", nullable = false)
    private Subject subject;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

    public void update(Subject subject, Integer price) {
        this.subject = subject;
        this.price = price;
    }
}
