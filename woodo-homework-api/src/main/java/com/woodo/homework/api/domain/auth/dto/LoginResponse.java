package com.woodo.homework.api.domain.auth.dto;

import com.woodo.homework.core.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private Long id;
    private String name;
    private String email;
    private String accessToken;

    public LoginResponse(Member member, String decryptedEmail, String accessToken) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = decryptedEmail;
        this.accessToken = accessToken;
    }
}
