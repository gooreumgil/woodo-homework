package com.woodo.homework.api.domain.auth.dto;

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
public class SignUpRequest {

    @Schema(defaultValue = "김우도")
    @NotEmpty(message = NAME_NOT_BE_NULL)
    private String name;

    @Schema(defaultValue = "email@email.com")
    @NotEmpty(message = EMAIL_NOT_BE_NULL)
    @Email(message = EMAIL_NOT_VALID)
    private String email;

    @Schema(defaultValue = "010-1111-2222")
    @NotEmpty(message = PHONE_NUMBER_NOT_BE_NULL)
    @Pattern(regexp = "^010-(\\d{3,4})-(\\d{4})$", message = PHONE_NUMBER_NOT_VALID)
    private String phoneNumber;

    @Schema(defaultValue = "password1!")
    @NotEmpty(message = PASSWORD_MUST_NOT_NULL)
    @Length(min = 6, max = 10, message = PASSWORD_LENGTH_NOT_VALID)
    @Pattern(regexp = "^(?:(?=.*\\d)(?=.*[a-zA-Z])|(?=.*[a-zA-Z])(?=.*[A-Z])|(?=.*\\d)(?=.*[A-Z])).+$", message = PASSWORD_NOT_VALID)
    private String password;

}
