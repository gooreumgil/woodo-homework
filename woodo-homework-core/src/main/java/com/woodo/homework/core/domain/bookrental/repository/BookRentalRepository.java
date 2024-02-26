package com.woodo.homework.core.domain.bookrental.repository;

import com.woodo.homework.core.domain.bookrental.BookRental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRentalRepository extends JpaRepository<BookRental, Long> {
}
