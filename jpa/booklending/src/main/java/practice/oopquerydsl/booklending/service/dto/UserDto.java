package practice.oopquerydsl.booklending.service.dto;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class UserDto {

    private String name;
    private String mobile;
    private String userType;
    private String code;
}
