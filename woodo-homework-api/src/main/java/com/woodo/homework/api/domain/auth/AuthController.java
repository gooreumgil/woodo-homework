package com.woodo.homework.api.domain.auth;

import com.woodo.homework.api.config.jwt.JwtProvider;
import com.woodo.homework.api.domain.auth.dto.LoginRequest;
import com.woodo.homework.api.domain.auth.dto.LoginResponse;
import com.woodo.homework.api.domain.auth.dto.SignUpRequest;
import com.woodo.homework.api.domain.auth.dto.SignUpResponse;
import com.woodo.homework.api.domain.auth.service.AuthService;
import com.woodo.homework.api.exception.HttpException;
import com.woodo.homework.api.exception.HttpExceptionCode;
import com.woodo.homework.core.domain.member.Member;
import com.woodo.homework.core.utils.AES256Util;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtProvider jwtProvider;
    private final AuthService authService;

    @Operation(description = "회원가입", summary = "회원가입")
    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {

        Member member = authService.signUp(signUpRequest);
        return ResponseEntity.ok(new SignUpResponse(member));

    }

    @Operation(description = "로그인", summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {

        Member attemptingLoginMember = authService.login(loginRequest);

        String decryptedEmail;
        try {
            decryptedEmail = AES256Util.decrypt(attemptingLoginMember.getEmail());
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, HttpExceptionCode.EMAIL_DECRYPT_FAIL, "이메일이 정상적이지 않습니다.");
        }

        String token = jwtProvider.createToken(attemptingLoginMember, decryptedEmail);

        return ResponseEntity.ok(new LoginResponse(attemptingLoginMember, decryptedEmail, token));

    }

}
