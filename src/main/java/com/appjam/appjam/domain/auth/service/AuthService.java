package com.appjam.appjam.domain.auth.service;
import com.appjam.appjam.domain.auth.entity.User;
import com.appjam.appjam.domain.auth.entity.enums.Role;
import com.appjam.appjam.domain.auth.repository.UserRepository;
import com.appjam.appjam.domain.auth.request.LoginRequest;
import com.appjam.appjam.domain.auth.request.SignupRequest;
import com.appjam.appjam.domain.auth.response.LoginResponse;
import com.appjam.appjam.global.config.TokenProvider;
import com.appjam.appjam.global.exception.HttpException;
import com.mysql.cj.exceptions.PasswordExpiredException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Transactional
    public void signupUser(SignupRequest request) {
        if(userRepository.existsUserByUserId(request.getUserId()))
            throw new HttpException(HttpStatus.BAD_REQUEST, "이미 해당 이름을 사용하는 멤버가 존재합니다.");
        User user = User.builder()
                .userId(request.getUserId())
                .nickName(request.getNickName())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(List.of(Role.ROLE_USER))
                .build();
        userRepository.save(user);
    }

    @Transactional
    public LoginResponse signinUser(LoginRequest request) {
        User user = userRepository.findByUserId(request.getUserId()).orElseThrow(() -> new RuntimeException("노 유저"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new PasswordExpiredException();
        return tokenProvider.generateTokenSet(user.getId());
    }

    @Transactional
    public User getCurrentUser() {
        return userRepository.findByUserId(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "해당 유저를 찾을 수 없습니다."));
    }
}
