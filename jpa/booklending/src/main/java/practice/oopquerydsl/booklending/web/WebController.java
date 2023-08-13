package practice.oopquerydsl.booklending.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import practice.oopquerydsl.booklending.service.UserService;
import practice.oopquerydsl.booklending.service.dto.BookInfoDto;
import practice.oopquerydsl.booklending.web.dto.BookRequestDto;
import practice.oopquerydsl.booklending.web.dto.UserRequestDto;

@RestController
@RequiredArgsConstructor
public class WebController {

    private final UserService userService;


    /**
     * This method is the interface for objects' cooperation among User, Librarian, Book, etc.
     * This receives a message to borrow the book with name, author information
     *
     */
    @PostMapping("/books/borrow")
    @ResponseBody
    public ResponseEntity<BookInfoDto> borrow(
                @ModelAttribute UserRequestDto user,
                @ModelAttribute BookRequestDto bookRequest) {

        BookInfoDto bookInfo = userService.borrow(bookRequest, user);

        return ResponseEntity.ok(bookInfo);
    }
}
