package com.woodo.homework.core.domain.bookrental;

import com.woodo.homework.core.audit.AuditingDomain;
import com.woodo.homework.core.domain.consignedbook.ConsignedBook;
import com.woodo.homework.core.domain.member.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookRental extends AuditingDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consigned_book_id")
    private ConsignedBook consignedBook;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrower_id")
    private Member borrower;

    private LocalDateTime rentalStartDate;
    private LocalDateTime rentalEndDate;

    public void setBorrower(Member borrower) {
        this.borrower = borrower;
        borrower.addBookRental(this);
    }

    public void setConsignedBook(ConsignedBook consignedBook) {
        this.consignedBook = consignedBook;
        consignedBook.addBookRental(this);
    }

}
