package com.woodo.homework.api.domain.bookrental;

import com.woodo.homework.api.security.MemberContext;
import com.woodo.homework.api.domain.bookrental.dto.BookRentalSaveResponse;
import com.woodo.homework.api.domain.bookrental.service.BookRentalService;
import com.woodo.homework.core.domain.bookrental.BookRental;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "도서대여 (Book Rentals)")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book-rentals")
public class BookRentalController {

    private final BookRentalService bookRentalService;

    @Operation(description = "도서대여", summary = "도서대여")
    @PostMapping
    public ResponseEntity<BookRentalSaveResponse> save(@RequestParam List<Long> consignedBookIds, @AuthenticationPrincipal MemberContext memberContext) {

        List<BookRental> savedBookRentalList = bookRentalService.save(memberContext.getId(), consignedBookIds);
        BookRentalSaveResponse bookRentalSaveResponse = new BookRentalSaveResponse();
        savedBookRentalList.forEach(savedBookRental -> {
            bookRentalSaveResponse.addBookRentalId(savedBookRental.getId());
        });

        return ResponseEntity.ok(bookRentalSaveResponse);

    }

}
