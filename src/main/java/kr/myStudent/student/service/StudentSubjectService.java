package kr.myStudent.student.service;

import kr.myStudent.student.domain.StudentSubjectEntity;
import kr.myStudent.student.domain.StudentSubjectRepository;
import kr.myStudent.student.dto.request.StudentSubjectCreateRequest;
import kr.myStudent.student.dto.request.StudentSubjectUpdateRequest;
import kr.myStudent.student.dto.response.StudentSubjectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentSubjectService {

    private final StudentSubjectRepository studentSubjectRepository;

    /** 등록 */
    public StudentSubjectResponse create(StudentSubjectCreateRequest req) {
        StudentSubjectEntity studentSubject = StudentSubjectEntity.builder()
                .studentId(req.getStudentId())
                .userId(req.getUserId())
                .subject(req.getSubject())
                .price(req.getPrice())
                .build();

        studentSubjectRepository.save(studentSubject);
        return StudentSubjectResponse.fromEntity(studentSubject);
    }

    /** 학생별 과목 조회 */
    public List<StudentSubjectResponse> getStudentSubjectList(Long studentId, String userId) {
        return studentSubjectRepository.findByStudentId(studentId, userId)
                .stream()
                .map(StudentSubjectResponse::fromEntity)
                .toList();
    }

    /** 수정 */
    public StudentSubjectResponse update(Long studentSubjectId, StudentSubjectUpdateRequest req) {
        StudentSubjectEntity studentSubject = studentSubjectRepository.findById(studentSubjectId)
                .orElseThrow(() -> new IllegalArgumentException("과목을 찾을 수 없습니다."));

        studentSubject.update(req.getSubject(), req.getPrice());
        studentSubjectRepository.save(studentSubject);

        return StudentSubjectResponse.fromEntity(studentSubject);
    }

    /** 삭제 */
    public void delete(Long studentSubjectId) {
        studentSubjectRepository.deleteByIdAndStudentId(studentSubjectId);
    }
}
