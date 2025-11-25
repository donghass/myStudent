package kr.myStudent.student.controller;

import kr.myStudent.common.response.CommonResponse;
import kr.myStudent.common.response.ResponseCode;
import kr.myStudent.student.dto.request.StudentSubjectCreateRequest;
import kr.myStudent.student.dto.request.StudentSubjectUpdateRequest;
import kr.myStudent.student.dto.response.StudentSubjectResponse;
import kr.myStudent.student.service.StudentSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student-subject")
public class StudentSubjectController {

    private final StudentSubjectService studentSubjectService;

    /** 과목 등록 */
    @PostMapping("")
    public ResponseEntity<CommonResponse<StudentSubjectResponse>> create(
            @RequestBody StudentSubjectCreateRequest req
    ) {
        StudentSubjectResponse result = studentSubjectService.create(req);

        return ResponseEntity.ok(
                CommonResponse.success(ResponseCode.SUCCESS, result)
        );
    }

    /** 학생의 과목 전체 조회 */
    @GetMapping("/{studentId}/{userId}")
    public ResponseEntity<CommonResponse<List<StudentSubjectResponse>>> getStudentSubjectList(
            @PathVariable Long studentId,
            @PathVariable String userId
    ) {
        List<StudentSubjectResponse> list = studentSubjectService.getStudentSubjectList(studentId, userId);

        return ResponseEntity.ok(
                CommonResponse.success(ResponseCode.SUCCESS, list)
        );
    }

    /** 과목 수정 */
    @PutMapping("/{studentSubjectId}")
    public ResponseEntity<CommonResponse<StudentSubjectResponse>> update(
            @PathVariable Long studentSubjectId,
            @RequestBody StudentSubjectUpdateRequest req
    ) {
        StudentSubjectResponse result = studentSubjectService.update(studentSubjectId, req);

        return ResponseEntity.ok(
                CommonResponse.success(ResponseCode.SUCCESS, result)
        );
    }

    /** 과목 삭제 */
    @DeleteMapping("/{studentSubjectId}")
    public ResponseEntity<CommonResponse<String>> delete(
            @PathVariable Long studentSubjectId
    ) {

        studentSubjectService.delete(studentSubjectId);

        return ResponseEntity.ok(
                CommonResponse.success(ResponseCode.SUCCESS, "삭제 완료")
        );
    }
}
