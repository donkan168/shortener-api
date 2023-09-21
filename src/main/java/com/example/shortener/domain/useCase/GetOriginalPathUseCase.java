package com.example.shortener.domain.useCase;

import com.example.shortener.adapter.dto.ShortenerResponse;
import com.example.shortener.adapter.out.repository.JpaLinkRepository;
import com.example.shortener.core.LinkEntity;
import com.example.shortener.domain.dto.Constants;
import com.example.shortener.exceptions.BussinessException;
import com.example.shortener.exceptions.ShortenerException;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Optional;


public class GetOriginalPathUseCase implements CasoUso<String, ShortenerResponse>{

    private static final String CODE_ENTITY_NOT_FOUND = "1404";
    private final JpaLinkRepository jpaLinkRepository;

    public GetOriginalPathUseCase(JpaLinkRepository jpaLinkRepository) {
        this.jpaLinkRepository = jpaLinkRepository;
    }

    @Override
    public ShortenerResponse execute(String shortPath) {

        return ShortenerResponse.builder().originalPath(
                String.valueOf(jpaLinkRepository.findByShortPath(shortPath)
                        .orElseThrow(()-> new ShortenerException(CODE_ENTITY_NOT_FOUND, shortPath))
                        .getOriginalPath())).build();
    }
}
