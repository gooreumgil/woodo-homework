package com.woodo.homework.api.domain.auth.service;

import com.woodo.homework.api.domain.auth.dto.LoginRequest;
import com.woodo.homework.api.domain.auth.dto.SignUpRequest;
import com.woodo.homework.api.exception.AuthorizationFailureException;
import com.woodo.homework.api.exception.HttpException;
import com.woodo.homework.api.exception.HttpExceptionCode;
import com.woodo.homework.core.domain.member.Member;
import com.woodo.homework.core.domain.member.repository.MemberRepository;
import com.woodo.homework.core.utils.AES256Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.woodo.homework.api.constants.ExceptionConstants.LOGIN_FAILED;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member signUp(SignUpRequest signUpRequest) {

        String encryptedEmail;

        try {
            encryptedEmail = AES256Util.encrypt(signUpRequest.getEmail());
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, HttpExceptionCode.EMAIL_ENCRYPT_FAIL, "정상적인 이메일이 아닙니다.");
        }

        Optional<Member> optionalMember = memberRepository.findByEmail(encryptedEmail);

        if (optionalMember.isPresent()) {
            throw new HttpException(HttpStatus.BAD_REQUEST, HttpExceptionCode.ALREADY_EXISTS_EMAIL, "이미 가입된 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());

        Member member = Member.create(signUpRequest.getName(), encryptedEmail, signUpRequest.getPhoneNumber(), encodedPassword);

        return memberRepository.save(member);

    }

    public Member login(LoginRequest loginRequest) {

        String password = loginRequest.getPassword();
        String encryptedEmail;

        try {
            encryptedEmail = AES256Util.encrypt(loginRequest.getEmail());
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, HttpExceptionCode.EMAIL_ENCRYPT_FAIL, "정상적인 이메일이 아닙니다.");
        }

        Optional<Member> optionalMember = memberRepository.findByEmail(encryptedEmail);
        if (optionalMember.isEmpty()) {
            throw new AuthorizationFailureException(LOGIN_FAILED);
        }

        Member member = optionalMember.get();
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new AuthorizationFailureException(LOGIN_FAILED);
        }

        return member;

    }

}
