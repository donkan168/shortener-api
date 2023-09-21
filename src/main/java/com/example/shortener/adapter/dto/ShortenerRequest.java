package com.example.shortener.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShortenerRequest {

    String username;
    String path;

}
