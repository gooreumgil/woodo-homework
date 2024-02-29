package com.woodo.homework.api.domain.bookrental.dto;

import com.woodo.homework.core.domain.bookrental.BookRental;
import com.woodo.homework.core.domain.consignedbook.ConsignedBook;
import com.woodo.homework.core.domain.member.Member;
import jakarta.persistence.*;
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
public class BookRentalResponse {

    private Long id;
    private BigDecimal rentalPrice;
    private Boolean returned;
    private LocalDateTime rentalStartDate;
    private LocalDateTime rentalEndDate;

    private BookRentalConsignedBookResponse book;

    public BookRentalResponse(BookRental bookRental) {
        this.id = bookRental.getId();
        this.rentalPrice = bookRental.getRentalPrice();
        this.returned = bookRental.getRentalEndDate() != null;
        this.book = new BookRentalConsignedBookResponse(bookRental.getConsignedBook());
        this.rentalStartDate = bookRental.getRentalStartDate();
        this.rentalEndDate = bookRental.getRentalEndDate();
    }
}
