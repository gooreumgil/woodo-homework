package com.woodo.homework.core.domain.member;

import com.woodo.homework.core.audit.AuditingDomain;
import com.woodo.homework.core.domain.book.Book;
import com.woodo.homework.core.domain.book.BookRental;
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

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Book> bookList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<BookRental> memberBookRentalList = new ArrayList<>();

}
