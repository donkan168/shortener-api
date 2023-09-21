package com.example.shortener.adapter.config;

import com.example.shortener.adapter.out.repository.AdapterLinkRepository;
import com.example.shortener.adapter.out.repository.JpaLinkRepository;
import com.example.shortener.domain.out.LinkRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RepositoryConfig {

    @Bean
    public LinkRepository linkRepository(JpaLinkRepository jpaLinkRepository) {
        return new AdapterLinkRepository(jpaLinkRepository);
    }
}
