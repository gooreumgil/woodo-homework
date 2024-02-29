package com.woodo.homework.api.domain.bookrental.dto;

import com.woodo.homework.core.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRentalConsignorResponse {

    private Long id;
    private String name;

    public BookRentalConsignorResponse(Member consignor) {
        this.id = consignor.getId();
        this.name = consignor.getName();
    }
}
