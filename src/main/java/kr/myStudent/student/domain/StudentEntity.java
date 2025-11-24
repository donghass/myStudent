package kr.myStudent.student.domain;

import jakarta.persistence.*;
import kr.myStudent.student.dto.request.StudentUpdateRequest;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ms_student")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;   // 학생 PK (자동 증가)

    @Column(name = "user_id", nullable = false)
    private String userId;      // 선생 아이디 (FK 역할)

    @Column(name = "name", length = 20, nullable = false)
    private String name;        // 학생이름

    @Column(name = "tel", length = 20)
    private String tel;         // 학생번호

    @Column(name = "age")
    private Integer age;        // 나이

    @Column(name = "memo", columnDefinition = "TEXT")
    private String memo;        // 메모

    @Column(name = "parent_tel", length = 20)
    private String parentTel;   // 학부모번호

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

    public void update(StudentUpdateRequest req) {
        this.name = req.getName();
        this.tel = req.getTel();
        this.age = req.getAge();
        this.memo = req.getMemo();
        this.parentTel = req.getParentTel();
        this.updatedAt = LocalDateTime.now();
    }
}
