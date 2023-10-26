package com.example.multipartfile.controller;

import com.example.multipartfile.dto.Fruit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
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
        String v1 = objectMapper.writeValueAsString(fruit);
        System.out.println(v1);

    }

    @PostMapping("/v1")
//    @PostMapping(path = "/v1", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Fruit apiV1(@RequestPart MultipartFile multipartFile, @ModelAttribute Fruit fruit) {
        boolean isEmpty = multipartFile.isEmpty();
        System.out.println("isEmpty = " + isEmpty);

        return fruit;
    }



}
