package com.appjam.appjam.domain.auth.service;

import com.appjam.appjam.domain.auth.repository.UserRepository;
import com.appjam.appjam.global.exception.HttpException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public AuthDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return new AuthDetails(
                userRepository.findById(Long.parseLong(id)).orElseThrow(() ->
                        new HttpException(HttpStatus.NOT_FOUND, "해당 유저를 찾을 수 없습니다.")
                )
        );
    }
}

