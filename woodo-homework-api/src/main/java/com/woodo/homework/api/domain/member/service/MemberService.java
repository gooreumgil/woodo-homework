package com.woodo.homework.api.domain.member.service;

import com.woodo.homework.api.domain.consignedbook.dto.ConsignedBookSaveRequest;
import com.woodo.homework.api.domain.member.dto.MemberSaveRequest;
import com.woodo.homework.core.domain.consignedbook.ConsignedBook;
import com.woodo.homework.core.domain.member.Member;
import com.woodo.homework.core.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member save(MemberSaveRequest memberSaveRequest) {

        String encodedPassword = passwordEncoder.encode(memberSaveRequest.getPassword());
        Member member = Member.create(memberSaveRequest.getName(), memberSaveRequest.getEmail(), memberSaveRequest.getPhoneNumber(), encodedPassword);

        return memberRepository.save(member);

    }

    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    public void addConsignedBook(Long id, ConsignedBookSaveRequest consignedBookSaveRequest) {
        Member member = findById(id).orElseThrow(() -> new RuntimeException(""));
        ConsignedBook consignedBook = ConsignedBook.create(consignedBookSaveRequest.getName(), consignedBookSaveRequest.getIsbn(), consignedBookSaveRequest.getRentalPrice());
        member.addConsignedBook(consignedBook);
    }
}
