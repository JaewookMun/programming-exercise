package practice.oopquerydsl.booklending.repository.dto;

import lombok.Builder;
import lombok.Data;
import practice.oopquerydsl.booklending.entity.BookStatus;
import practice.oopquerydsl.booklending.web.dto.BookRequestDto;

@Data @Builder
public class BookSearchCondition {
    private String name;
    private String author;
    private String isbn;
    private BookStatus status;


    public static BookSearchCondition of(BookRequestDto dto) {
        return of(dto, BookStatus.ALL);
    }

    public static BookSearchCondition of(BookRequestDto dto, BookStatus bookStatus) {
        return new BookSearchConditionBuilder()
                .name(dto.getBookName())
                .author(dto.getAuthor())
                .status(bookStatus)
                .build();
    }
}
