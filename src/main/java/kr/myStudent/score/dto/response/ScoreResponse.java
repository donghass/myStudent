package kr.myStudent.score.dto.response;

import kr.myStudent.score.domain.ScoreEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScoreResponse {

    private Long scoreId;
    private String userId;
    private Long studentId;
    private String subject;
    private Double score;
    private String testName;
    private String testDate;

    public static ScoreResponse fromEntity(ScoreEntity e) {
        return ScoreResponse.builder()
                .scoreId(e.getScoreId())
                .userId(e.getUserId())
                .studentId(e.getStudentId())
                .subject(e.getSubject().name())
                .score(e.getScore())
                .testName(e.getTestName())
                .testDate(e.getTestDate() != null ? e.getTestDate().toString() : null)
                .build();
    }
}
