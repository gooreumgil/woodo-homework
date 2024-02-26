package com.woodo.homework.api.domain.consignedbook.service;

import com.woodo.homework.core.domain.consignedbook.repository.ConsignedBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ConsignedBookService {

    private final ConsignedBookRepository consignedBookRepository;

}
