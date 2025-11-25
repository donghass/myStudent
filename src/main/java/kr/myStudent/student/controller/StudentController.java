package kr.myStudent.student.controller;

import kr.myStudent.common.response.CommonResponse;
import kr.myStudent.common.response.ResponseCode;
import kr.myStudent.student.dto.request.StudentCreateRequest;
import kr.myStudent.student.dto.request.StudentUpdateRequest;
import kr.myStudent.student.dto.response.StudentResponse;
import kr.myStudent.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    /** 등록 */
    @PostMapping
    public ResponseEntity<CommonResponse<StudentResponse>> create(@RequestBody StudentCreateRequest req) {

        StudentResponse result = studentService.create(req);

        return ResponseEntity.ok(
                CommonResponse.success(ResponseCode.SUCCESS, result)
        );
    }

    /** 단건 조회 */
    @GetMapping("/{studentId}/{userId}")
    public ResponseEntity<CommonResponse<StudentResponse>> getOne(
            @PathVariable Long studentId,
            @PathVariable String userId
    ) {

        StudentResponse result = studentService.getOne(studentId, userId);

        return ResponseEntity.ok(
                CommonResponse.success(ResponseCode.SUCCESS, result)
        );
    }

    /** 전체 조회 */
    @GetMapping
    public ResponseEntity<CommonResponse<List<StudentResponse>>> getAll(
            @RequestParam String userId
    ) {
        List<StudentResponse> result = studentService.getAllByUserId(userId);

        return ResponseEntity.ok(
                CommonResponse.success(ResponseCode.SUCCESS, result)
        );
    }

    /** 수정 */
    @PutMapping("/{studentId}/{userId}")
    public ResponseEntity<CommonResponse<StudentResponse>> update(
            @PathVariable Long studentId,
            @PathVariable String userId,
            @RequestBody StudentUpdateRequest req
    ) {

        StudentResponse result = studentService.update(studentId, userId, req);

        return ResponseEntity.ok(
                CommonResponse.success(ResponseCode.SUCCESS, result)
        );
    }

    /** 삭제 */
    @DeleteMapping("/{studentId}/{userId}")
    public ResponseEntity<CommonResponse<String>> delete(
            @PathVariable Long studentId,
            @PathVariable String userId
    ) {

        studentService.delete(studentId, userId);

        return ResponseEntity.ok(
                CommonResponse.success(ResponseCode.SUCCESS, "삭제 완료")
        );
    }
}
