package kr.myStudent.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ms_jwt")
public class RefreshTokenEntity {

    @Id
    private String userId;        // 1:1 매핑

    @Column(nullable = false)
    private String refreshToken;

    public static RefreshTokenEntity tokenUpdate(String id, String refreshToken) {
        RefreshTokenEntity token = new RefreshTokenEntity();
        token.userId = id;
        token.refreshToken = refreshToken;
        return token;
    }
}
