package com.woodo.homework.api.common.dto;

import com.woodo.homework.api.domain.consignedbook.dto.ConsignedBookResponse;
import com.woodo.homework.core.domain.consignedbook.ConsignedBook;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PageResponse<T> {

    private PageInfo pageInfo;
    private List<T> list;

    public PageResponse(Page<?> page, List<T> dtoList) {
        this.pageInfo = PageInfo.create(page);
        this.list = dtoList;
    }
}
