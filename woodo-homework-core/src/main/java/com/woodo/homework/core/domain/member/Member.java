package com.woodo.homework.core.domain.member;

import com.woodo.homework.core.audit.AuditingDomain;
import com.woodo.homework.core.domain.consignedbook.ConsignedBook;
import com.woodo.homework.core.domain.bookrental.BookRental;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "members")
public class Member extends AuditingDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;

    @OneToMany(mappedBy = "consignor", cascade = CascadeType.ALL)
    private List<ConsignedBook> consignedBookList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<BookRental> memberBookRentalList = new ArrayList<>();

    public static Member create(String name, String email, String phoneNumber, String encodedPassword) {
        Member member = new Member();
        member.name = name;
        member.email = email;
        member.phoneNumber = phoneNumber;
        member.password = encodedPassword;
        return member;
    }

    public void addConsignedBook(ConsignedBook consignedBook) {
        this.consignedBookList.add(consignedBook);
        consignedBook.setConsignor(this);
    }
}
