package practice.oopquerydsl.booklending.web.dto;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class UserRequestDto {
    private String userName;
    private String mobile;
    private String code;
}
