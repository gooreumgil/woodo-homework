package com.woodo.homework.core.domain.bookrental.repository;

import com.woodo.homework.core.domain.bookrental.BookRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BookRentalRepository extends JpaRepository<BookRental, Long> {

    @Query("select br from BookRental br join fetch br.consignedBook cb where cb.isRented is true and br.rentalStartDate < :before10Seconds")
    List<BookRental> findAllByRentalStartDateBefore(LocalDateTime before10Seconds);

}
