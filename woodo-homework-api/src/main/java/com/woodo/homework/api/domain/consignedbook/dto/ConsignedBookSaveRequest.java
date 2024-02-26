package com.woodo.homework.api.domain.consignedbook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsignedBookSaveRequest {

    private String name;
    private String isbn;
    private BigDecimal rentalPrice;

}
