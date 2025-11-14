package kr.myStudent.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "ms_user")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA용 기본 생성자
@AllArgsConstructor // 모든 필드 생성자
public class UserEntity {

    @Id
    @Email
    @NotBlank
    @Column(name = "user_id", nullable = false)
    private String userId;

    private String password;

    private String name;

    private String tel;

    @Builder.Default
    private String status = "1";   // 사용중 1, 미사용 2

    @Builder.Default
    private String role = "USER";   // 권한: USER 또는 ADMIN

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();
}
