package com.woodo.homework.core.domain.consignedbook.repository;

import com.woodo.homework.core.domain.consignedbook.ConsignedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConsignedBookRepository extends JpaRepository<ConsignedBook, Long> {

    @Query("select cb from ConsignedBook cb join fetch cb.consignor where cb.isRented is false  and cb.id in :ids")
    List<ConsignedBook> findAllByIdInAndIsRentedFalseJoinedConsignor(List<Long> ids);

}
