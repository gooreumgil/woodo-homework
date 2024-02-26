package com.woodo.homework.api.domain.bookrental;

import com.woodo.homework.api.domain.bookrental.service.BookRentalService;
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
    public ResponseEntity<?> save(@RequestParam Long memberId, @RequestParam List<Long> consignedBookIds) {
        bookRentalService.save(memberId, consignedBookIds);
        return ResponseEntity.ok().build();
    }

}
