package kr.myStudent.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Subject {
    KOREAN("국어"),
    ENGLISH("영어"),
    MATH("수학");

    private final String label;

    Subject(String label) {
        this.label = label;
    }

    public String getName() {
        return this.name();
    }
}
