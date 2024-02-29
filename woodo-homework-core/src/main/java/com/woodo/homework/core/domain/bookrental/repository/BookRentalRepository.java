package com.woodo.homework.core.domain.bookrental.repository;

import com.woodo.homework.core.domain.bookrental.BookRental;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BookRentalRepository extends JpaRepository<BookRental, Long> {

    @Query("select br from BookRental br join fetch br.consignedBook cb where br.rentalEndDate is null and br.rentalStartDate < :before10Seconds")
    List<BookRental> findAllByRentalStartDateBefore(LocalDateTime before10Seconds);

    @Query(value = "select br from BookRental br join fetch br.consignedBook cb join fetch cb.consignor where br.borrower.id = :borrowerId",
            countQuery = "select br from BookRental br join br.consignedBook cb join cb.consignor where br.borrower.id = :borrowerId")
    Page<BookRental> findAllByBorrowerId(Long borrowerId, Pageable pageable);

}
