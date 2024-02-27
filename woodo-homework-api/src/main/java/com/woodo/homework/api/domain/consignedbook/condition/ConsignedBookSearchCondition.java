package com.woodo.homework.api.domain.consignedbook.condition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsignedBookSearchCondition {

    private String bookName;
    private String consignorName;
    private BigDecimal minRentalPrice;
    private BigDecimal maxRentalPrice;

}
