package kr.myStudent.progress.service;

import kr.myStudent.progress.domain.*;
import kr.myStudent.progress.dto.request.ProgressCreateRequest;
import kr.myStudent.progress.dto.request.ProgressUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgressService {

    private final ProgressRepository repository;

    public ProgressEntity create(ProgressCreateRequest req) {

        ProgressEntity entity = ProgressEntity.builder()
                .studentId(req.getStudentId())
                .userId(req.getUserId())
                .subject(req.getSubject())
                .lessonDate(LocalDateTime.parse(req.getLessonDate()))
                .content(req.getContent())
                .homework(req.getHomework())
                .memo(req.getMemo())
                .build();

        return repository.save(entity);
    }

    public ProgressEntity update(Long id, ProgressUpdateRequest req) {
        ProgressEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("progress not found"));

        entity.updateProgress(
                req.getSubject(),
                LocalDateTime.parse(req.getLessonDate()),
                req.getContent(),
                req.getHomework(),
                req.getMemo()
        );

        return repository.save(entity);
    }

    public List<ProgressEntity> getLatest(String userId) {
        return repository.findLatestProgressByUserId(userId);
    }

    public List<ProgressEntity> getByStudent(String userId, Long studentId) {
        return repository.findByStudent(userId, studentId);
    }
}
