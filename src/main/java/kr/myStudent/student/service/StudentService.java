package kr.myStudent.student.service;

import kr.myStudent.student.domain.StudentEntity;
import kr.myStudent.student.domain.StudentId;
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

        StudentId id = StudentId.builder()
                .studentId(req.getStudentId())
                .userId(req.getUserId())
                .build();

        StudentEntity entity = StudentEntity.builder()
                .id(id)
                .name(req.getName())
                .tel(req.getTel())
                .age(req.getAge())
                .memo(req.getMemo())
                .parentTel(req.getParentTel())
                .build();

        studentRepository.save(entity);

        return StudentResponse.fromEntity(entity);
    }

    /** 단건 조회 */
    public StudentResponse getOne(StudentId id) {

        StudentEntity entity = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("학생을 찾을 수 없습니다."));

        return StudentResponse.fromEntity(entity);
    }

    /** 선생별 학생 전체 조회 */
    public List<StudentResponse> getAllByUserId(String userId) {

        List<StudentEntity> list = studentRepository.findAllByUserId(userId);

        return list.stream()
                .map(StudentResponse::fromEntity)
                .collect(Collectors.toList());
    }

    /** 수정 */
    public StudentResponse update(StudentId id, StudentUpdateRequest req) {

        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("학생을 찾을 수 없습니다."));

        student.update(req);

        studentRepository.save(student);

        return StudentResponse.fromEntity(student);
    }

    /** 삭제 */
    public void delete(StudentId id) {
        studentRepository.deleteById(id);
    }
}
