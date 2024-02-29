package com.woodo.homework.api.scheduler;

import com.woodo.homework.api.domain.bookrental.service.BookRentalService;
import com.woodo.homework.api.domain.consignedbook.service.ConsignedBookService;
import com.woodo.homework.core.domain.bookrental.BookRental;
import com.woodo.homework.core.domain.consignedbook.ConsignedBook;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ConsignedBookReturnScheduler {

    private final BookRentalService bookRentalService;

    @Scheduled(fixedDelay = 10000L)
    @Transactional
    public void returnBookSchedule() {
        List<BookRental> bookRentalList = bookRentalService.findAllBookRentalDueForReturn();
        for (BookRental bookRental : bookRentalList) {
            ConsignedBook consignedBook = bookRental.getConsignedBook();
            consignedBook.returnBook();
            bookRental.setRentalEndDate(LocalDateTime.now());
        }
    }

}
