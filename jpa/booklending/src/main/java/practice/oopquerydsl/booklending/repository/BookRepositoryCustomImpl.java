package practice.oopquerydsl.booklending.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import practice.oopquerydsl.booklending.entity.Book;
import practice.oopquerydsl.booklending.entity.BookStatus;
import practice.oopquerydsl.booklending.repository.dto.BookSearchCondition;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;
import static practice.oopquerydsl.booklending.entity.QBook.book;

@Repository
@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Book> search(BookSearchCondition condition) {

        return queryFactory
                .selectFrom(book)
                .where(
                        bookNameEq(condition.getName()),
                        bookAuthorEq(condition.getAuthor()),
                        bookStatusEq(condition.getStatus())
                )
                .orderBy(book.name.asc())
                .fetch();
    }

    private BooleanExpression bookNameEq(String name) {
        return hasText(name) ? book.name.eq(name) : null;
    }

    private BooleanExpression bookStatusEq(BookStatus status) {
        return status != null && !status.equals(BookStatus.ALL) ? book.status.eq(status) : null;
    }

    private BooleanExpression bookAuthorEq(String author) {
        return hasText(author) ? book.author.eq(author) : null;
    }
}
