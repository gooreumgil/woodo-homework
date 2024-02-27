package com.woodo.homework.api.domain.consignedbook.dto;

import com.woodo.homework.core.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsignedBookConsignorResponse {

    private Long id;
    private String consignorName;

    public ConsignedBookConsignorResponse(Member consignor) {
        this.id = consignor.getId();
        this.consignorName = consignor.getName();
    }
}
