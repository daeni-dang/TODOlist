package com.gadaeun.server.dto;

import lombok.Getter;

public class RequestDto {

    @Getter
    public static class UserInfoDto {

        private String email;
        private String pwd;

        public UserInfoDto(String email, String pwd) {
            this.email = email;
            this.pwd = pwd;
        }
    }
}
