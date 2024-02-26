package com.woodo.homework.api.common.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PageResponse<T> {

    private PageInfo pageInfo;
    private List<T> list;

    public PageResponse(Page<T> page) {
        this.pageInfo = PageInfo.create(page);
        this.list = page.getContent();
    }

}
