package com.woodo.homework.api.domain.consignedbook.dto;

import com.woodo.homework.core.domain.consignedbook.ConsignedBook;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsignedBookResponse {

    private Long id;
    private String bookName;
    private String isbn;
    private BigDecimal rentalPrice;
    private int rentalCount;
    private boolean isRented;

    private ConsignedBookConsignorResponse consignor;

    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    public ConsignedBookResponse(ConsignedBook consignedBook) {
        this.id = consignedBook.getId();
        this.bookName = consignedBook.getName();
        this.isbn = consignedBook.getIsbn();
        this.rentalPrice = consignedBook.getRentalPrice();
        this.rentalCount = consignedBook.getRentalCount();
        this.isRented = consignedBook.isRented();
        this.consignor = new ConsignedBookConsignorResponse(consignedBook.getConsignor());
        this.createdDateTime = consignedBook.getCreatedDateTime();
        this.updatedDateTime = consignedBook.getUpdatedDateTime();
    }
}
