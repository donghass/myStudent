package kr.myStudent.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.myStudent.redis.repository.TokenRedisRepository;
import kr.myStudent.user.domain.UserEntity;
import kr.myStudent.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final TokenRedisRepository redisRepo;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {

        String header = req.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7);

            // 1) 블랙리스트 체크
            if (redisRepo.isBlacklisted(token)) {
                chain.doFilter(req, res);
                return;
            }

            try {
                // 2) AccessToken 검증 (예외 발생 시 catch 블록으로 이동)
                jwtUtil.validateAndExtractClaims(token);

                // 검증 성공 시 정보 추출
                String userId = jwtUtil.extractId(token);
                String role = jwtUtil.extractRole(token);

                UserEntity user = userRepository.findById(userId).orElse(null);

                if (user != null) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            userId,
                            null,
                            List.of(new SimpleGrantedAuthority(role)));

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }

            } catch (ExpiredJwtException e) {
                // 토큰 만료 시 401 응답 및 에러 코드 반환
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                res.setContentType(MediaType.APPLICATION_JSON_VALUE);
                res.setCharacterEncoding("UTF-8");

                String json = objectMapper.writeValueAsString(Map.of(
                        "code", "EXPIRED_TOKEN",
                        "message", "Access Token Expired"));
                res.getWriter().write(json);
                return; // 필터 체인 중단

            } catch (JwtException e) {
                // 기타 토큰 오류
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                res.setContentType(MediaType.APPLICATION_JSON_VALUE);
                res.setCharacterEncoding("UTF-8");

                String json = objectMapper.writeValueAsString(Map.of(
                        "code", "INVALID_TOKEN",
                        "message", "Invalid Token"));
                res.getWriter().write(json);
                return; // 필터 체인 중단
            }
        }

        chain.doFilter(req, res);
    }
}
