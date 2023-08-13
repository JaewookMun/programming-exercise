package practice.oopquerydsl.booklending.web.dto;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class BookRequestDto {
    private String bookName;
    private String author;
}
