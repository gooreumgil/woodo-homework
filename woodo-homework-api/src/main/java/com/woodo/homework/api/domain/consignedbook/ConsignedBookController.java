package com.woodo.homework.api.domain.consignedbook;

import com.woodo.homework.api.common.dto.PageResponse;
import com.woodo.homework.api.security.MemberContext;
import com.woodo.homework.api.domain.consignedbook.condition.ConsignedBookSearchCondition;
import com.woodo.homework.api.domain.consignedbook.dto.ConsignedBookResponse;
import com.woodo.homework.api.domain.consignedbook.dto.ConsignedBookSaveRequest;
import com.woodo.homework.api.domain.consignedbook.service.ConsignedBookService;
import com.woodo.homework.api.domain.member.service.MemberService;
import com.woodo.homework.core.domain.consignedbook.ConsignedBook;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

import java.math.BigDecimal;
import java.util.List;

@Tag(name = "위탁도서 (Consigned Books)")
@RestController
@RequestMapping("/api/v1/consigned-books")
@RequiredArgsConstructor
public class ConsignedBookController {

    private final ConsignedBookService consignedBookService;
    private final MemberService memberService;

    @Operation(description = "도서 위탁하기", summary = "도서 위탁하기")
    @PostMapping
    public ResponseEntity<?> save(@RequestBody ConsignedBookSaveRequest consignedBookSaveRequest, @AuthenticationPrincipal MemberContext memberContext) {

        ConsignedBook consignedBook = memberService.addConsignedBook(memberContext.getId(), consignedBookSaveRequest);
        return ResponseEntity.ok(new ConsignedBookResponse(consignedBook));
    }

    @Operation(description = "위탁 도서 전체 조회", summary = "위탁 도서 전체 조회")
    @GetMapping
    public ResponseEntity<PageResponse<ConsignedBookResponse>> findAllConsignedBooks(
            @Parameter(description = "도서명") @RequestParam(required = false) String bookName,
            @Parameter(description = "위탁자 이름") @RequestParam(required = false) String consignorName,
            @Parameter(description = "대여가격 최소") @RequestParam(required = false) BigDecimal minRentalPrice,
            @Parameter(description = "대여가격 최대") @RequestParam(required = false) BigDecimal maxRentalPrice,
            @ParameterObject @PageableDefault(size = 20, sort = "createdDateTime", direction = Sort.Direction.DESC) Pageable pageable,
            @AuthenticationPrincipal MemberContext memberContext) {

        ConsignedBookSearchCondition consignedBookSearchCondition = new ConsignedBookSearchCondition(bookName, consignorName, minRentalPrice, maxRentalPrice);
        if (memberContext != null) {
            consignedBookSearchCondition.setConsignorId(memberContext.getId());
        }

        Page<ConsignedBook> consignedBookPage = consignedBookService.findAll(consignedBookSearchCondition, pageable);
        List<ConsignedBookResponse> consignedBookResponses = consignedBookPage.map(ConsignedBookResponse::new).stream().toList();

        return ResponseEntity.ok(new PageResponse<>(consignedBookPage, consignedBookResponses));

    }

}
