package com.gadaeun.server.user;

import com.gadaeun.server.dto.RequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional  // 테스트용으로 생성 후 롤백
public class LoginIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserService userService;

    @BeforeEach
    public void setup() {
        userService.createUser(new RequestDto.UserInfoDto("abcd@naver.com", "1234"));
    }

    @Test
    public void 로그인_성공() throws Exception {
        this.mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("email", "abcd@naver.com")
                .param("pwd", "1234"))
                .andExpect(status().isOk()
        );
    }

    @Test
    public void 로그인_실패_회원아님() throws Exception {
        this.mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("email", "eded@naver.com")
                .param("pwd", "1234"))
                .andExpect(status().isUnauthorized()
        );
    }

    @Test
    public void 로그인_실패_비밀번호틀림() throws Exception {
        this.mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("email", "abcd@naver.com")
                        .param("pwd", "5567"))
                .andExpect(status().isUnauthorized()
        );
    }

}
