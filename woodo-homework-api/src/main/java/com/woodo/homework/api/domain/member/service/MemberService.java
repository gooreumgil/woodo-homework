package com.woodo.homework.api.domain.member.service;

import com.woodo.homework.api.domain.consignedbook.dto.ConsignedBookSaveRequest;
import com.woodo.homework.api.exception.HttpException;
import com.woodo.homework.api.exception.HttpExceptionCode;
import com.woodo.homework.core.domain.consignedbook.ConsignedBook;
import com.woodo.homework.core.domain.consignedbook.repository.ConsignedBookRepository;
import com.woodo.homework.core.domain.member.Member;
import com.woodo.homework.core.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final ConsignedBookRepository consignedBookRepository;

    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    @Transactional
    public ConsignedBook addConsignedBook(Long id, ConsignedBookSaveRequest consignedBookSaveRequest) {
        Member member = findById(id)
                .orElseThrow(() ->
                        new HttpException(
                                HttpStatus.BAD_REQUEST,
                                HttpExceptionCode.NOT_EXISTS,
                                "존재하지 않는 member 입니다."));

        ConsignedBook consignedBook = ConsignedBook.create(
                consignedBookSaveRequest.getName(),
                consignedBookSaveRequest.getIsbn(),
                consignedBookSaveRequest.getRentalPrice());

        member.addConsignedBook(consignedBook);
        return consignedBookRepository.save(consignedBook);
    }
}
