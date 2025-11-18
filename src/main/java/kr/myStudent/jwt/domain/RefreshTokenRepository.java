package kr.myStudent.jwt.domain;

public interface RefreshTokenRepository {
    void save(RefreshTokenEntity refreshTokenEntity);
}
