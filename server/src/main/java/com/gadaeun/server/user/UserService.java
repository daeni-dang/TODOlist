package com.gadaeun.server.user;

import com.gadaeun.server.dto.RequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Users createUser(RequestDto.UserInfoDto dto) {
        Users user = Users.builder()
                .email(dto.getEmail())  // TODO: 이메일 암호화
                .pwd(passwordEncoder.encode(dto.getPwd()))
            .build();
        userRepository.save(user);
        log.info("회원 생성 email = " + dto.getEmail());
        return user;
    }
}
