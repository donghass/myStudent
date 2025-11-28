# MyStudent Backend 프로젝트 규칙

## 프로젝트 개요
- **프레임워크**: Spring Boot 3.3.4
- **Java 버전**: 17
- **빌드 도구**: Gradle
- **데이터베이스**: MySQL (TiDB Cloud)
- **캐시**: Redis
- **인증**: JWT (Spring Security)
- **ORM**: JPA (Hibernate) + QueryDSL
- **유틸리티**: Lombok

## 코딩 컨벤션

### 1. 패키지 구조
```
kr.myStudent
├── {domain}/
│   ├── controller/     # REST API 컨트롤러
│   ├── service/         # 비즈니스 로직
│   ├── repository/      # 데이터 접근 계층
│   ├── domain/          # 엔티티
│   └── dto/
│       ├── request/     # 요청 DTO
│       └── response/    # 응답 DTO
├── common/              # 공통 클래스
│   ├── exception/       # 예외 처리
│   └── response/        # 공통 응답
├── config/              # 설정 클래스
├── jwt/                 # JWT 관련
└── enums/               # 열거형
```

### 2. 컨트롤러 작성 규칙
- `@RestController` 사용
- `@RequiredArgsConstructor`로 의존성 주입 (Lombok)
- 모든 응답은 `CommonResponse<T>`로 래핑
- `ResponseEntity<CommonResponse<T>>` 반환
- 예시:
```java
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;
    
    @PostMapping("/signup")
    public ResponseEntity<CommonResponse<SignUpResponse>> signup(@RequestBody SignUpRequest request) {
        SignUpResponse response = userService.signup(request);
        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, response));
    }
}
```

### 3. 서비스 작성 규칙
- 인터페이스와 구현체 분리 (선택적)
- `@Service` 어노테이션
- `@RequiredArgsConstructor` 사용
- 비즈니스 로직 처리 및 트랜잭션 관리
- 예외는 `BusinessException` 또는 `GlobalExceptionHandler`로 처리

### 4. 리포지토리 작성 규칙
- JPA Repository는 `JpaRepository` 상속
- QueryDSL 사용 시 `CustomRepository` 인터페이스와 `Impl` 구현체 분리
- 복잡한 쿼리는 QueryDSL 사용
- 예시:
```java
public interface ProgressRepository extends JpaRepository<ProgressEntity, Long>, ProgressRepositoryCustom {
}

public interface ProgressRepositoryCustom {
    List<ProgressEntity> findCustomQuery(...);
}

@RequiredArgsConstructor
public class ProgressRepositoryImpl implements ProgressRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    // QueryDSL 구현
}
```

### 5. 엔티티 작성 규칙
- `@Entity` 어노테이션
- Lombok `@Data`, `@Builder` 사용
- JPA 어노테이션 사용 (`@Id`, `@GeneratedValue`, `@Column` 등)
- 패키지: `kr.myStudent.{domain}.domain`

### 6. DTO 작성 규칙
- Request DTO: `{Domain}Request`, `{Domain}UpdateRequest` 등
- Response DTO: `{Domain}Response`
- Lombok `@Data`, `@Builder` 사용
- Validation 어노테이션 사용 (`@NotNull`, `@NotBlank` 등)

### 7. 공통 응답 패턴
- 모든 API 응답은 `CommonResponse<T>` 사용
- 성공: `CommonResponse.success(ResponseCode.SUCCESS, data)`
- 실패: `CommonResponse.fail(ResponseCode.ERROR_CODE)`
- `ResponseCode` enum에서 응답 코드 관리

### 8. 예외 처리
- 커스텀 예외: `BusinessException` 사용
- 전역 예외 처리: `GlobalExceptionHandler`에서 처리
- `ResponseCode`로 에러 코드 관리

### 9. JWT 인증
- JWT 필터: `JwtFilter` 사용
- 토큰 유틸리티: `JwtUtil`
- 토큰 서비스: `JwtService`
- Security 설정: `SecurityConfig`

### 10. 네이밍 규칙
- 클래스: PascalCase
- 메서드/변수: camelCase
- 상수: UPPER_SNAKE_CASE
- 패키지: 소문자, 점 구분

### 11. 데이터베이스
- TiDB Cloud 사용 (MySQL 호환)
- JPA `ddl-auto: update` (개발 환경)
- 타임존: `Asia/Seoul`
- HikariCP 커넥션 풀 사용

### 12. Redis
- Spring Data Redis 사용
- 호스트: `redis` (Docker 환경)
- 포트: `6379`

### 13. 테스트
- JUnit 5 사용
- Testcontainers로 통합 테스트
- `@SpringBootTest` 사용

## 개발 환경 설정
- Docker Compose로 Redis 실행
- `docker-compose.yml` 파일 위치: 프로젝트 루트
- 실행: `docker-compose up -d` (프로젝트 루트에서)

## 주의사항
- 모든 API는 `CommonResponse`로 래핑하여 반환
- 프론트엔드와 데이터 구조 일치 확인
- JWT 토큰은 Authorization 헤더에서 `Bearer {token}` 형태로 받음
- Refresh Token은 `Refresh-Token` 헤더로 전송
- 타임존은 항상 `Asia/Seoul` 사용

## 빌드 및 실행
- `./gradlew build`: 빌드
- `./gradlew bootRun`: 실행
- `./gradlew test`: 테스트 실행
