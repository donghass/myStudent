package kr.myStudent.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TextbookStatus {
    IN_PROGRESS("진행중"),
    COMPLETED("완료");

    private final String label;

    TextbookStatus(String label) {
        this.label = label;
    }

    public String getName() {
        return this.name();
    }
}
