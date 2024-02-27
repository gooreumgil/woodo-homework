package com.woodo.homework.api.domain.bookrental;

import com.woodo.homework.api.domain.bookrental.dto.BookRentalSaveResponse;
import com.woodo.homework.api.domain.bookrental.service.BookRentalService;
import com.woodo.homework.core.domain.bookrental.BookRental;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book-rentals")
public class BookRentalController {

    private final BookRentalService bookRentalService;

    @PostMapping
    public ResponseEntity<BookRentalSaveResponse> save(@RequestParam Long memberId, @RequestParam List<Long> consignedBookIds) {
        List<BookRental> savedBookRentalList = bookRentalService.save(memberId, consignedBookIds);
        BookRentalSaveResponse bookRentalSaveResponse = new BookRentalSaveResponse();
        savedBookRentalList.forEach(savedBookRental -> {
            bookRentalSaveResponse.addBookRentalId(savedBookRental.getId());
        });
        return ResponseEntity.ok(bookRentalSaveResponse);
    }

}
