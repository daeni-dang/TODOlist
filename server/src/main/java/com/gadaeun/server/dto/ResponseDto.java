package com.gadaeun.server.dto;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ResponseDto {

    private String message; // 필수 사항
    private Map<String, String> data;   // 선택 사항

    public ResponseDto(ResponseDtoBuilder builder) {
        this.message = builder.message;
        this.data = builder.data;
    }

    public static class ResponseDtoBuilder {
        private final String message;
        private HashMap<String, String> data;

        public ResponseDtoBuilder(String message) {
            this.message = message;
        }

        public ResponseDtoBuilder data(HashMap<String, String> data) {
            this.data = data;
            return this;
        }

        public ResponseDto build() {
            return new ResponseDto(this);
        }
    }
}
