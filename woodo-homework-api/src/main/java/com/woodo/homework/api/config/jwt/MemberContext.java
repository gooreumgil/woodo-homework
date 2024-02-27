package com.woodo.homework.api.config.jwt;

import com.woodo.homework.core.domain.member.Member;
import io.jsonwebtoken.Claims;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
public class MemberContext {

    private Long id;
    private String email;
    private String name;

    public MemberContext(Claims claims) {
        int memberId = (Integer) claims.get("id");
        this.id = (long) memberId;
        this.email = (String) claims.get("email");
        this.name = (String) claims.get("name");
    }
}
