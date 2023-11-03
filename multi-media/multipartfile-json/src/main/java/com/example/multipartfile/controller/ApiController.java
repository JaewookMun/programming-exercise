package com.example.multipartfile.controller;

import com.example.multipartfile.dto.Fruit;
import com.example.multipartfile.dto.RequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ApiController {

    private final ObjectMapper objectMapper;

    @PostConstruct
    private void init() throws JsonProcessingException {
        System.out.println("start");
        Fruit fruit = new Fruit();
        fruit.setName("apple");
        fruit.setColor("red");

        RequestDto requestDto = new RequestDto();
        requestDto.setCity("Seoul");
        requestDto.setFruit(fruit);

        String jsonExample1 = objectMapper.writeValueAsString(fruit);
        String jsonExample2 = objectMapper.writeValueAsString(requestDto);
        System.out.println("jsonExample1 = " + jsonExample1);
        System.out.println("jsonExample2 = " + jsonExample2);
    }

    // fruit 이름으로 {"name":"apple","color":"red"}를 보내면
    // 400 error가 발생하지만 StringToRequestConverter.class를 사용해서 값들을 받을 수 있다.
    // 이 때, 각 필드이름으로 값들을 전송하면 객체를 자동으로 초기화할 수 있다.
    @PostMapping("/v1")
//    @PostMapping(path = "/v1", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Fruit apiV1(@RequestPart MultipartFile multipartFile, @ModelAttribute Fruit fruit) {
        boolean isEmpty = multipartFile.isEmpty();
        if (isEmpty) System.out.println("isEmpty = " + isEmpty);
        System.out.println("fruit = " + fruit);

        return fruit;
    }

    @PostMapping("/v2")
    public RequestDto apiV2(@RequestPart MultipartFile multipartFile, @ModelAttribute RequestDto requestDto) {
        boolean isEmpty = multipartFile.isEmpty();
        if (isEmpty) System.out.println("isEmpty = " + isEmpty);
        System.out.println("requestDto = " + requestDto);

        return requestDto;
    }


}
