package com.woodo.homework.api.domain.bookrental.service;

import com.woodo.homework.core.domain.bookrental.BookRental;
import com.woodo.homework.core.domain.bookrental.repository.BookRentalRepository;
import com.woodo.homework.core.domain.consignedbook.ConsignedBook;
import com.woodo.homework.core.domain.consignedbook.repository.ConsignedBookRepository;
import com.woodo.homework.core.domain.member.Member;
import com.woodo.homework.core.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookRentalService {

    private final BookRentalRepository bookRentalRepository;
    private final ConsignedBookRepository consignedBookRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void save(Long memberId, List<Long> consignedBookIds) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException(""));
        List<ConsignedBook> consignedBookList = consignedBookRepository.findAllByIdIn(consignedBookIds);

    }
}
