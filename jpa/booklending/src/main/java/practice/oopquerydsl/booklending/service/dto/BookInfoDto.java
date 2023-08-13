package practice.oopquerydsl.booklending.service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data @Builder
public class BookInfoDto {
    private String bookName;
    private String author;
    private String isbn;
    private LocalDate dueDate;

    private boolean available;

    private LocalDate reservedDate;

}
