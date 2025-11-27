package kr.myStudent.textbook.service;

import kr.myStudent.textbook.domain.TextbookEntity;
import kr.myStudent.enums.TextbookStatus;
import kr.myStudent.textbook.dto.request.TextbookCreateRequest;
import kr.myStudent.textbook.dto.response.TextbookResponse;
import kr.myStudent.textbook.repository.TextbookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TextbookService {

    private final TextbookRepository textbookRepository;

    @Transactional
    public TextbookResponse create(TextbookCreateRequest request) {
        TextbookEntity textbook = TextbookEntity.builder()
                .studentId(request.getStudentId())
                .userId(request.getUserId())
                .title(request.getTitle())
                .subject(request.getSubject())
                .totalUnit(request.getTotalUnit())
                .currentUnit(0)
                .status(TextbookStatus.IN_PROGRESS)
                .build();

        textbookRepository.save(textbook);
        return TextbookResponse.fromEntity(textbook);
    }

    public List<TextbookResponse> getByStudent(Long studentId, String userId) {
        return textbookRepository.findByStudentIdAndUserId(studentId, userId).stream()
                .map(TextbookResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
