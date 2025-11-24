package kr.myStudent.user.execption;

import kr.myStudent.common.response.BaseResponseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements BaseResponseCode {

    // 409 - 아이디 중복체크
    INVALID_USER_ID(409, "이미 가입된 아이디", "이미 사용중인 아이디입니다."),

    // 401 - 리소스 없음
    USER_NOT_FOUND(401, "아이디 또는 비밀번호가 올바르지 않음.", "아이디 또는 비밀번호가 올바르지 않습니다.");


    private final int code;         // HTTP 상태 코드
    private final String status;    // 커스텀 코드
    private final String message;   // 사용자 메시지
}