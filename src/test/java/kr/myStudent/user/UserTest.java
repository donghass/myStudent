package kr.myStudent.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.myStudent.dto.request.LoginRequest;
import kr.myStudent.dto.request.SignUpRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = true)
@Testcontainers
@Slf4j
public class UserTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입 → 로그인 → 인증 API 전체 흐름 테스트")
    void signup_login_me_flow() throws Exception {

        // 1. 회원가입 요청

        SignUpRequest signUpRequest = SignUpRequest.builder()
                .userId("test2@example.com")
                .password("1234")
                .name("홍길동")
                .tel("01012345678")
                .build();

        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signUpRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.userId").value("test2@example.com"));



        // 2. 로그인 요청 → JWT 발급

        LoginRequest loginRequest = LoginRequest.builder()
                .userId("test2@example.com")
                .password("1234")
                .build();

        String token = mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.accessToken").exists())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // 응답 JSON에서 accessToken만 꺼내기
        String accessToken = objectMapper.readTree(token)
                .path("data")
                .path("accessToken")
                .asText();

        log.info("SignUpRequest ID: {}", signUpRequest.getUserId());
        log.info("SignUpRequest PW: {}", signUpRequest.getPassword());

        log.info("LoginRequest ID: {}", loginRequest.getUserId());
        log.info("LoginRequest PW: {}", loginRequest.getPassword());

        log.info("Access Token: {}", accessToken);
        log.info("Raw Token Response JSON: {}", token);
    }
}
