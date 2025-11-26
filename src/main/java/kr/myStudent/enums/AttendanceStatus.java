package kr.myStudent.enums;

public enum AttendanceStatus {
    ATTENDED("출석"),
    ABSENT("결석"),
    LATE("지각"),
    EARLY_LEAVE("조퇴");

    private final String description;

    AttendanceStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
