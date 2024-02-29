package com.woodo.homework.api.domain.consignedbook.dto;

import com.woodo.homework.api.constants.ValidationExceptionConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import static com.woodo.homework.api.constants.ValidationExceptionConstants.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsignedBookSaveRequest {

    @Schema(defaultValue = "전설로 떠나는 월가의 영웅")
    @NotEmpty(message = CONSIGNED_BOOK_NAME_NOT_BE_NULL)
    private String name;

    @Schema(defaultValue = " 9788957825945")
    @NotEmpty(message = CONSIGNED_BOOK_ISBN_NOT_BE_NULL)
    @Pattern(regexp = "^(?:ISBN(?:-13)?:?\\ )?97[89]-\\d{1,5}-\\d{1,7}-\\d{1,6}-\\d$", message = CONSIGNED_BOOK_ISBN_NOT_VALID)
    private String isbn;

    @Schema(defaultValue = "5000")
    @NotEmpty(message = RENTAL_PRICE_NOT_BE_NULL)
    @DecimalMin(value = "1000", message = RENTAL_PRICE_MINIMUM)
    private BigDecimal rentalPrice;

}
