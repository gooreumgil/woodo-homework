package com.woodo.homework.api.domain.member;

import com.woodo.homework.api.domain.consignedbook.dto.ConsignedBookSaveRequest;
import com.woodo.homework.api.domain.member.dto.MemberResponse;
import com.woodo.homework.api.domain.member.dto.MemberSaveRequest;
import com.woodo.homework.api.domain.member.service.MemberService;
import com.woodo.homework.core.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberResponse> save(@RequestBody MemberSaveRequest memberSaveRequest) {
        Member member = memberService.save(memberSaveRequest);
        return ResponseEntity.ok(new MemberResponse(member));
    }

    @PostMapping("/{id}/consigned-book")
    public ResponseEntity<?> addConsignedBook(@PathVariable Long id, @RequestBody ConsignedBookSaveRequest consignedBookSaveRequest) {
        memberService.addConsignedBook(id, consignedBookSaveRequest);
        return ResponseEntity.ok().build();
    }


}
