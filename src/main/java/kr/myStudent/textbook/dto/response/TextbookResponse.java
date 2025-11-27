package kr.myStudent.textbook.dto.response;

import kr.myStudent.enums.Subject;
import kr.myStudent.textbook.domain.TextbookEntity;
import kr.myStudent.enums.TextbookStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
@Builder
public class TextbookResponse {
    private Long id;
    private Long studentId;
    private String title;
    private Subject subject;
    private Integer totalUnit;
    private Integer currentUnit;
    private TextbookStatus status;
    private String lastUpdate;

    public static TextbookResponse fromEntity(TextbookEntity entity) {
        return TextbookResponse.builder()
                .id(entity.getTextbookId())
                .studentId(entity.getStudentId())
                .title(entity.getTitle())
                .subject(entity.getSubject())
                .totalUnit(entity.getTotalUnit())
                .currentUnit(entity.getCurrentUnit())
                .status(entity.getStatus())
                .lastUpdate(entity.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
    }
}
