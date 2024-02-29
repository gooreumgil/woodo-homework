package com.woodo.homework.api.domain.consignedbook.service;

import com.woodo.homework.api.domain.consignedbook.condition.ConsignedBookSearchCondition;
import com.woodo.homework.api.domain.consignedbook.repository.ConsignedBookQuerydslRepository;
import com.woodo.homework.core.domain.consignedbook.ConsignedBook;
import com.woodo.homework.core.domain.consignedbook.repository.ConsignedBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ConsignedBookService {

    private final ConsignedBookRepository consignedBookRepository;
    private final ConsignedBookQuerydslRepository consignedBookQuerydslRepository;

    public Page<ConsignedBook> findAll(ConsignedBookSearchCondition condition, Pageable pageable) {
        return consignedBookQuerydslRepository.findAllByCondition(condition, pageable);
    }

}
