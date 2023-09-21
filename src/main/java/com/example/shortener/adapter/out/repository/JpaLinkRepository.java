package com.example.shortener.adapter.out.repository;

import com.example.shortener.core.LinkEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JpaLinkRepository extends CrudRepository<LinkEntity, Long> {
    Optional<LinkEntity> findByShortPath(String shortPath);
    Optional<LinkEntity> findByOriginalPath(String originalPath);
}