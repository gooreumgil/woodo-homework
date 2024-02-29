package com.woodo.homework.api.domain.bookrental;

import com.woodo.homework.api.common.dto.PageResponse;
import com.woodo.homework.api.domain.bookrental.dto.BookRentalResponse;
import com.woodo.homework.api.security.MemberContext;
import com.woodo.homework.api.domain.bookrental.dto.BookRentalSaveResponse;
import com.woodo.homework.api.domain.bookrental.service.BookRentalService;
import com.woodo.homework.core.domain.bookrental.BookRental;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    @Operation(description = "대여한 도서 히스토리", summary = "대여한 도서 히스토리")
    @GetMapping
    public ResponseEntity<PageResponse<BookRentalResponse>> findAll(
            @AuthenticationPrincipal MemberContext memberContext,
            @ParameterObject @PageableDefault(size = 20, sort = "createdDateTime", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<BookRental> bookRentalPage = bookRentalService.findAllByBorrowerId(memberContext.getId(), pageable);
        List<BookRentalResponse> bookRentalResponseList = bookRentalPage.map(BookRentalResponse::new).stream().toList();
        return ResponseEntity.ok(new PageResponse<>(bookRentalPage, bookRentalResponseList));
    }

    @Operation(description = "도서 대여하기", summary = "도서 대여하기")
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
