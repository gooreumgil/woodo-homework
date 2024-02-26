package com.woodo.homework.api.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberSaveRequest {

    private String name;
    private String email;
    private String phoneNumber;
    private String password;

}