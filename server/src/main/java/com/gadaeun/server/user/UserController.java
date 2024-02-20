package com.gadaeun.server.user;

import com.gadaeun.server.dto.RequestDto;
import com.gadaeun.server.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signup(@RequestBody RequestDto.UserInfoDto dto) {
        try {
            userService.createUser(dto);
            log.info("회원가입 성공 " + dto.getEmail());
            return new ResponseEntity<>(
                    new ResponseDto.ResponseDtoBuilder("회원가입 성공").build(),
                    HttpStatus.OK);
        } catch (Exception e) {
            log.error("회원가입 실패 " + dto.getEmail());
            return new ResponseEntity<>(
                    new ResponseDto.ResponseDtoBuilder("회원가입 실패").build(),
                    HttpStatus.UNAUTHORIZED
            );
        }
    }
}
