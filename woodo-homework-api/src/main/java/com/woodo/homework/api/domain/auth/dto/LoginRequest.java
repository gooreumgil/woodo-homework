package com.woodo.homework.api.domain.auth.dto;

import com.woodo.homework.api.constants.ExceptionConstants;
import com.woodo.homework.api.constants.ValidationExceptionConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import static com.woodo.homework.api.constants.ValidationExceptionConstants.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @Schema(defaultValue = "email@email.com")
    @Email(message = EMAIL_NOT_VALID)
    @NotEmpty(message = EMAIL_NOT_BE_NULL)
    private String email;

    @Schema(defaultValue = "password1!")
    @NotEmpty(message = PASSWORD_MUST_NOT_NULL)
    private String password;

}
