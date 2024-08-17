package com.appjam.appjam.domain.auth.controller;
import com.appjam.appjam.domain.auth.request.LoginRequest;
import com.appjam.appjam.domain.auth.request.SignupRequest;
import com.appjam.appjam.domain.auth.response.LoginResponse;
import com.appjam.appjam.domain.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<LoginRequest> signup(@RequestBody @Valid SignupRequest request) {
        authService.signupUser(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signin")
    public ResponseEntity<LoginResponse> signin(@Valid @RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.signinUser(request));
    }
}
