package com.example.shortener.domain.useCase;

import com.example.shortener.adapter.dto.ShortenerRequest;
import com.example.shortener.adapter.dto.ShortenerResponse;
import com.example.shortener.adapter.out.repository.JpaLinkRepository;
import com.example.shortener.core.LinkEntity;
import com.example.shortener.domain.dto.Constants;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class GenerateShortPathUseCase implements CasoUso<ShortenerRequest, ShortenerResponse>{

    private final JpaLinkRepository jpaLinkRepository;

    public GenerateShortPathUseCase(JpaLinkRepository jpaLinkRepository) {
        this.jpaLinkRepository = jpaLinkRepository;
    }

    @Override
    public ShortenerResponse execute(ShortenerRequest request) {
        LinkEntity linkEntity = LinkEntity.builder()
                .username(request.getUsername())
                .originalPath(request.getPath())
                .shortPath(reducePath(request.getPath())).build();

        LinkEntity result = jpaLinkRepository
                .findByOriginalPath(request.getPath())
                .orElseGet(() -> jpaLinkRepository.save(linkEntity));

        try {
            return ShortenerResponse.builder().shortPath(
                    Constants.BASE_DOMAIN.concat(
                            URLEncoder.encode(result.getShortPath(), "UTF-8"))).build();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private String reducePath(String path) {
        return String.valueOf(DigestUtils.md5Digest(path.getBytes()));
    }


}
