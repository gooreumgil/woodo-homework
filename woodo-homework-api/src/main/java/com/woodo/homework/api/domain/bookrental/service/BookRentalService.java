package com.woodo.homework.api.domain.bookrental.service;

import com.woodo.homework.api.exception.HttpException;
import com.woodo.homework.api.exception.HttpExceptionCode;
import com.woodo.homework.core.domain.bookrental.BookRental;
import com.woodo.homework.core.domain.bookrental.repository.BookRentalRepository;
import com.woodo.homework.core.domain.consignedbook.ConsignedBook;
import com.woodo.homework.core.domain.consignedbook.repository.ConsignedBookRepository;
import com.woodo.homework.core.domain.member.Member;
import com.woodo.homework.core.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookRentalService {

    private final BookRentalRepository bookRentalRepository;
    private final ConsignedBookRepository consignedBookRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public List<BookRental> save(Long memberId, List<Long> consignedBookIds) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new HttpException(
                        HttpStatus.BAD_REQUEST,
                        HttpExceptionCode.NOT_EXISTS,
                        "존재하지 않는 member 입니다."));

        List<ConsignedBook> consignedBookList = consignedBookRepository.findAllByIdInJoinedConsignor(consignedBookIds);

        if (consignedBookList.isEmpty()) {
            throw new HttpException(
                    HttpStatus.BAD_REQUEST, HttpExceptionCode.NOT_EXISTS, "도서가 존재하지 않습니다.");
        }

        Optional<ConsignedBook> ownBook = consignedBookList.stream().filter(consignedBook -> consignedBook.getConsignor().getId().equals(memberId)).findAny();
        if (ownBook.isPresent()) {
            throw new HttpException(HttpStatus.BAD_REQUEST, HttpExceptionCode.OWN_BOOK_CANNOT_CONSIGN, "본인 소유의 도서는 대여할 수 없습니다.");
        }
        List<ConsignedBook> rentedConsignedBook = consignedBookList.stream().filter(ConsignedBook::isRented).toList();
        if (!rentedConsignedBook.isEmpty()) {
            List<String> rentedBookNameList = rentedConsignedBook.stream().map(ConsignedBook::getName).toList();
            String rentedBookNames = String.join(", ", rentedBookNameList);
            throw new HttpException(HttpStatus.BAD_REQUEST, HttpExceptionCode.ALREADY_BORROWED, "이미 대여된 책입니다 [ " + rentedBookNames + " ]");
        }

        List<BookRental> savedBookRentalList = new ArrayList<>();

        consignedBookList.forEach(consignedBook -> {
            BookRental bookRental = new BookRental();
            bookRental.setBorrower(member);
            bookRental.setConsignedBook(consignedBook);
            bookRental.setRentalStartDate(LocalDateTime.now());
            consignedBook.rentBook();
            consignedBook.plusRentalCount();
            savedBookRentalList.add(bookRentalRepository.save(bookRental));
        });

        return savedBookRentalList;

    }

    public List<BookRental> findAllBookRentalDueForReturn() {
        return bookRentalRepository.findAllByRentalStartDateBefore(LocalDateTime.now().minusSeconds(10));
    }

}
