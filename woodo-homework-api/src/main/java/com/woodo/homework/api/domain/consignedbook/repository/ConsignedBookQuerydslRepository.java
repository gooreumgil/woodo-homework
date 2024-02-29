package com.woodo.homework.api.domain.consignedbook.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.woodo.homework.api.domain.consignedbook.condition.ConsignedBookSearchCondition;
import com.woodo.homework.core.domain.consignedbook.ConsignedBook;
import com.woodo.homework.core.utils.Querydsl5RepositorySupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

import static com.woodo.homework.core.domain.consignedbook.QConsignedBook.consignedBook;

public interface ConsignedBookQuerydslRepository {
    Page<ConsignedBook> findAllByCondition(ConsignedBookSearchCondition condition, Pageable pageable);
}

@Repository
class ConsignedBookQuerydslRepositoryImpl extends Querydsl5RepositorySupport implements ConsignedBookQuerydslRepository {

    public ConsignedBookQuerydslRepositoryImpl() {
        super(ConsignedBook.class);
    }

    @Override
    public Page<ConsignedBook> findAllByCondition(ConsignedBookSearchCondition condition, Pageable pageable) {
        return applyPagination(pageable, query -> query
                .selectFrom(consignedBook)
                .leftJoin(consignedBook.consignor).fetchJoin()
                .where(
                        consignedBook.isRented.eq(false),
                        containsBookName(condition.getBookName()),
                        consignorNameEq(condition.getConsignorName()),
                        minRentalPrice(condition.getMinRentalPrice()),
                        maxRentalPrice(condition.getMaxRentalPrice()),
                        excludeOwnerBook(condition.getConsignorId())
                )

        );
    }

    private BooleanExpression containsBookName(String bookName) {
        return StringUtils.hasText(bookName) ? consignedBook.name.contains(bookName) : null;
    }

    private BooleanExpression consignorNameEq(String consignorName) {
        return StringUtils.hasText(consignorName) ? consignedBook.consignor.name.eq(consignorName) : null;
    }

    private BooleanExpression minRentalPrice(BigDecimal minPrice) {
        return minPrice != null ? consignedBook.rentalPrice.gt(minPrice) : null;
    }

    private BooleanExpression maxRentalPrice(BigDecimal maxPrice) {
        return maxPrice != null ? consignedBook.rentalPrice.lt(maxPrice) : null;
    }

    private BooleanExpression excludeOwnerBook(Long consignorId) {
        return consignorId != null ? consignedBook.consignor.id.ne(consignorId) : null;
    }

}