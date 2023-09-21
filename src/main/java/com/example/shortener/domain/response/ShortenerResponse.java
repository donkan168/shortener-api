package com.example.shortener.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShortenerResponse {

    String username;
    String originalPath;
    String shortPath;
}
