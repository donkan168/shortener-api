package com.example.shortener.domain.configuration;

import com.example.shortener.adapter.out.repository.JpaLinkRepository;
import com.example.shortener.domain.useCase.GenerateShortPathUseCase;
import com.example.shortener.domain.useCase.GetOriginalPathUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CasoUsoConfiguracion {

    @Bean
    public GenerateShortPathUseCase generateShortPathUseCase(JpaLinkRepository jpaLinkRepository) {
        return new GenerateShortPathUseCase(jpaLinkRepository);
    }

    @Bean
    public GetOriginalPathUseCase getShortPathUseCase(JpaLinkRepository jpaLinkRepository) {
        return new GetOriginalPathUseCase(jpaLinkRepository);
    }
}
