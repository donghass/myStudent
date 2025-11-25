package kr.myStudent.student.service;

import kr.myStudent.student.domain.StudentEntity;
import kr.myStudent.student.dto.request.StudentCreateRequest;
import kr.myStudent.student.dto.request.StudentUpdateRequest;
import kr.myStudent.student.dto.response.StudentResponse;
import kr.myStudent.student.domain.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    /** 등록 */
    public StudentResponse create(StudentCreateRequest req) {

        StudentEntity student = StudentEntity.builder()
                .userId(req.getUserId())
                .name(req.getName())
                .tel(req.getTel())
                .age(req.getAge())
                .memo(req.getMemo())
                .parentTel(req.getParentTel())
                .build();

        studentRepository.save(student);

        return StudentResponse.fromEntity(student);
    }

    /** 학생 단건 조회 */
    public StudentResponse getOne(Long studentId, String userId) {

        StudentEntity student = studentRepository.findByStudent(studentId, userId)
                .orElseThrow(() -> new IllegalArgumentException("학생을 찾을 수 없습니다."));

        return StudentResponse.fromEntity(student);
    }

    /** 선생별 학생 전체 조회 */
    public List<StudentResponse> getAllByUserId(String userId) {

        List<StudentEntity> list = studentRepository.findAllByUserId(userId);

        return list.stream()
                .map(StudentResponse::fromEntity)
                .collect(Collectors.toList());
    }

    /** 수정 */
    public StudentResponse update(Long studentId, String userId, StudentUpdateRequest req) {

        StudentEntity student = studentRepository.findByStudent(studentId, userId)
                .orElseThrow(() -> new IllegalArgumentException("학생을 찾을 수 없습니다."));

        student.update(req);

        studentRepository.save(student);

        return StudentResponse.fromEntity(student);
    }

    /** 삭제 */
    public void delete(Long studentId, String userId) {
        studentRepository.deleteByStudent(studentId, userId);
    }
}
