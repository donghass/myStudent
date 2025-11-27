package kr.myStudent.textbook.domain;

import jakarta.persistence.*;
import kr.myStudent.enums.Subject;
import kr.myStudent.enums.TextbookStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "ms_textbook")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TextbookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long textbookId;

    @Column(nullable = false)
    private Long studentId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Subject subject;

    @Column(nullable = false)
    private Integer totalUnit;

    @Column(nullable = false)
    @Builder.Default
    private Integer currentUnit = 0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private TextbookStatus status = TextbookStatus.IN_PROGRESS;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public void updateCurrentUnit(Integer currentUnit) {
        this.currentUnit = currentUnit;
    }

    public void updateStatus(TextbookStatus status) {
        this.status = status;
    }
}
