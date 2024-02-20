package com.gadaeun.server.user;

import com.gadaeun.server.dto.RequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
@Transactional
class UserServiceTest {

    // Test 주체
    @InjectMocks
    UserService userService;

    // Test 협력자 (가짜 객체를 만듦)
    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    void 회원가입_성공() {
        //given
        Users user = Users.builder()
                .email("abcd@naver.com")
                .pwd("1234")
                .build();
        //when
        Users newUser = userService.createUser(new RequestDto.UserInfoDto(user.getEmail(), user.getPwd()));
        //then
        Assertions.assertThat(newUser.getEmail()).isEqualTo(user.getEmail());
    }
}