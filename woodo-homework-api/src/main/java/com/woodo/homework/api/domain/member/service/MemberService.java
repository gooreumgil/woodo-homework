package com.woodo.homework.api.domain.member.service;

import com.woodo.homework.api.domain.consignedbook.dto.ConsignedBookSaveRequest;
import com.woodo.homework.core.domain.consignedbook.ConsignedBook;
import com.woodo.homework.core.domain.member.Member;
import com.woodo.homework.core.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    @Transactional
    public ConsignedBook addConsignedBook(Long id, ConsignedBookSaveRequest consignedBookSaveRequest) {
        Member member = findById(id).orElseThrow(() -> new RuntimeException(""));
        ConsignedBook consignedBook = ConsignedBook.create(consignedBookSaveRequest.getName(), consignedBookSaveRequest.getIsbn(), consignedBookSaveRequest.getRentalPrice());
        member.addConsignedBook(consignedBook);
        memberRepository.save(member);
        return consignedBook;
    }
}
