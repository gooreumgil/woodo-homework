package com.woodo.homework.core.domain.consignedbook.repository;

import com.woodo.homework.core.domain.consignedbook.ConsignedBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsignedBookRepository extends JpaRepository<ConsignedBook, Long> {
    List<ConsignedBook> findAllByIdIn(List<Long> ids);
}
