package com.woodo.homework.api.domain.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @Schema(defaultValue = "email3@email.com")
    private String email;

    @Schema(defaultValue = "rkskek123")
    private String password;

}
