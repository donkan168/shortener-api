package com.example.shortener.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ExceptionDTO {
    private String code;
    private String message;
}
