package kr.myStudent.domain.jwt;

public interface RefreshTokenRepository {
    void save(RefreshTokenEntity refreshTokenEntity);
}
