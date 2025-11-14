package kr.myStudent.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.myStudent.common.response.ResponseCode;
import kr.myStudent.controller.UserController;
import kr.myStudent.domain.UserRepository;
import kr.myStudent.dto.request.LoginRequest;
import kr.myStudent.dto.request.SignUpRequest;
import kr.myStudent.dto.response.LoginResponse;
import kr.myStudent.dto.response.SignUpResponse;
import kr.myStudent.jwt.JwtUtil;
import kr.myStudent.service.UserService;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class UserTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtUtil jwtUtil;

    @Autowired
    private ObjectMapper objectMapper;

    // UserService만 Mock 처리 (Controller 단위 테스트)
    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    @DisplayName("회원가입 성공 테스트")
    void signup_success() throws Exception {

        // Instancio로 랜덤 Request 생성 (필드 원하는 값으로 override 가능)
        SignUpRequest request = Instancio.of(SignUpRequest.class)
                .set(Select.field("id"), "testUser")
                .set(Select.field("email"), "test@example.com")
                .set(Select.field("password"), "1234")
                .set(Select.field("name"), "홍길동")
                .create();

        // Mock Response
        SignUpResponse mockResponse = SignUpResponse.builder()
                .id("test@example.com")
                .name("홍길동")
                .message("회원가입 성공")
                .build();

        Mockito.when(userService.signup(any(SignUpRequest.class)))
                .thenReturn(mockResponse);

        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResponseCode.SUCCESS.getCode()))
                .andExpect(jsonPath("$.data.id").value("testUser"))
                .andExpect(jsonPath("$.data.email").value("test@example.com"))
                .andExpect(jsonPath("$.data.name").value("홍길동"));
    }

    @Test
    @DisplayName("로그인 성공 테스트")
    void login_success() throws Exception {

        // Instancio로 생성
        LoginRequest request = Instancio.of(LoginRequest.class)
                .set(Select.field("id"), "testUser")
                .set(Select.field("password"), "1234")
                .create();

        LoginResponse mockResponse = LoginResponse.builder()
                .id("testUser")
                .accessToken("mockAccessTokenValue")
                .refreshToken("mockRefreshTokenValue")
                .message("로그인 성공")
                .build();

        Mockito.when(userService.login(any(LoginRequest.class)))
                .thenReturn(mockResponse);

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResponseCode.SUCCESS.getCode()))
                .andExpect(jsonPath("$.data.id").value("testUser"))
                .andExpect(jsonPath("$.data.accessToken").value("mockAccessTokenValue"))
                .andExpect(jsonPath("$.data.refreshToken").value("mockRefreshTokenValue"))
                .andExpect(jsonPath("$.data.message").value("로그인 성공"));
    }


}
