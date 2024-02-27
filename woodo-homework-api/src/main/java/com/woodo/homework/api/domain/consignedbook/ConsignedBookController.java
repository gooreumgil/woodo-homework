package com.woodo.homework.api.domain.consignedbook;

import com.woodo.homework.api.common.dto.PageInfo;
import com.woodo.homework.api.common.dto.PageResponse;
import com.woodo.homework.api.domain.consignedbook.condition.ConsignedBookSearchCondition;
import com.woodo.homework.api.domain.consignedbook.dto.ConsignedBookResponse;
import com.woodo.homework.api.domain.consignedbook.service.ConsignedBookService;
import com.woodo.homework.core.domain.consignedbook.ConsignedBook;
import com.woodo.homework.core.domain.consignedbook.repository.ConsignedBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/consigned-books")
@RequiredArgsConstructor
public class ConsignedBookController {

    private final ConsignedBookService consignedBookService;

    @GetMapping
    public ResponseEntity<PageResponse<ConsignedBookResponse>> findAllConsignedBooks(
            @RequestParam(required = false) String bookName,
            @RequestParam(required = false) String consignorName,
            @RequestParam(required = false) BigDecimal minRentalPrice,
            @RequestParam(required = false) BigDecimal maxRentalPrice,
            @PageableDefault(size = 20, sort = "createdDateTime", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<ConsignedBook> consignedBookPage = consignedBookService.findAll(
                new ConsignedBookSearchCondition(bookName, consignorName, minRentalPrice, maxRentalPrice), pageable);

        List<ConsignedBookResponse> consignedBookResponses = consignedBookPage.map(ConsignedBookResponse::new).stream().toList();

        return ResponseEntity.ok(new PageResponse<>(consignedBookPage, consignedBookResponses));

    }

}
