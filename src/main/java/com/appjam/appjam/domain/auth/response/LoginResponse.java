package com.appjam.appjam.domain.auth.response;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    private String access;
    private String refresh;
}
