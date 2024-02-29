package com.woodo.homework.api.domain.bookrental.dto;

import com.woodo.homework.core.domain.consignedbook.ConsignedBook;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRentalConsignedBookResponse {

    private Long id;
    private String bookName;
    private String isbn;
    private BookRentalConsignorResponse consignor;


    public BookRentalConsignedBookResponse(ConsignedBook consignedBook) {
        this.id = consignedBook.getId();
        this.bookName = consignedBook.getName();
        this.isbn = consignedBook.getIsbn();
        this.consignor = new BookRentalConsignorResponse(consignedBook.getConsignor());
    }
}
