package com.woodo.homework.api.domain.bookrental.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRentalSaveResponse {

    private List<Long> boolRentalIds = new ArrayList<>();

    public void addBookRentalId(Long bookRentalId) {
        this.boolRentalIds.add(bookRentalId);
    }

}
