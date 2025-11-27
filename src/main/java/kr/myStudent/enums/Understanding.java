package kr.myStudent.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Understanding {
    HIGH("상"),
    MEDIUM("중"),
    LOW("하");

    private final String label;

    Understanding(String label) {
        this.label = label;
    }

    public String getName() {
        return this.name();
    }
}
