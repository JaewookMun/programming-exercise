package com.example.multipartfile.config;

import com.example.multipartfile.dto.Fruit;
import com.example.multipartfile.dto.RequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StringToRequestConverterSecond implements Converter<String, RequestDto> {

    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public RequestDto convert(String source) {
        return objectMapper.readValue(source, RequestDto.class);
    }

    // https://stackoverflow.com/questions/52818107/how-to-send-the-multipart-file-and-json-data-to-spring-boot
}
