package com.woodo.homework.core.domain.consignedbook;

import com.woodo.homework.core.audit.AuditingDomain;
import com.woodo.homework.core.domain.bookrental.BookRental;
import com.woodo.homework.core.domain.member.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ConsignedBook extends AuditingDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String isbn;
    private BigDecimal rentalPrice;
    private Integer rentalCount;
    private boolean isRented;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consignor_id")
    private Member consignor;

    @OneToMany(mappedBy = "consignedBook", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<BookRental> bookRentalList = new ArrayList<>();

    public static ConsignedBook create(String name, String isbn, BigDecimal rentalPrice) {
        ConsignedBook consignedBook = new ConsignedBook();
        consignedBook.name = name;
        consignedBook.isbn = isbn;
        consignedBook.rentalPrice = rentalPrice;
        return consignedBook;
    }

    public void setConsignor(Member consignor) {
        this.consignor = consignor;
    }
}
