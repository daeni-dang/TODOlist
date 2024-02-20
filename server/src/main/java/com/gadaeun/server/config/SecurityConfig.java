package com.gadaeun.server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gadaeun.server.jwt.TokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private TokenProvider tokenProvider;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                    auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .usernameParameter("email")
                        .passwordParameter("pwd")
                        .loginProcessingUrl("/api/auth/login")  // POST로 요청을 받기 위한 설정
                        .permitAll()
                        .successHandler(((request, response, authentication) -> {
                            response.setContentType("application/json;charset=UTF-8");
                            response.setStatus(HttpServletResponse.SC_OK);

                            // JWT 토큰 생성
                            String token = tokenProvider.createToken(authentication);

                            // 헤더에 토큰 추가
                            response.setHeader("Authorization", "Bearer " + token);

                            // 응답 데이터 구성
                            Map<String, Object> data = new HashMap<>();
                            data.put("message", "로그인 성공");

                            // 응답 JSON 변환 및 전송
                            String jsonResponse = new ObjectMapper().writeValueAsString(data);
                            response.getWriter().write(jsonResponse);
                        }))
                        .failureHandler(((request, response, exception) -> {
                            response.setContentType("application/json;charset=UTF-8");
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                            Map<String, Object> data = new HashMap<>();
                            data.put("message", "회원가입 되지 않은 이메일 또는 비밀번호가 잘못되었습니다.");
                            String jsonResponse = new ObjectMapper().writeValueAsString(data);
                            response.getWriter().write(jsonResponse);
                        }))
                );

        return http.build();
    }
}
