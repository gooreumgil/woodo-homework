package com.woodo.homework.api.domain.member.dto;

import com.woodo.homework.core.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponse {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    public MemberResponse(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.phoneNumber = member.getPhoneNumber();
        this.createdDateTime = member.getCreatedDateTime();
        this.updatedDateTime = member.getUpdatedDateTime();
    }
}