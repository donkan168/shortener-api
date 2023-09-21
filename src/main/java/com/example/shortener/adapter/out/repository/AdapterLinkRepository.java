package com.example.shortener.adapter.out.repository;

import com.example.shortener.core.LinkEntity;
import com.example.shortener.domain.out.LinkRepository;

import java.util.ArrayList;
import java.util.List;

public class AdapterLinkRepository implements LinkRepository {

   private JpaLinkRepository jpaLinkRepository;

    public AdapterLinkRepository(JpaLinkRepository jpaLinkRepository) {
        this.jpaLinkRepository = jpaLinkRepository;
    }

    @Override
    public List<LinkEntity> getPath(String path) {
        List<LinkEntity> result = new ArrayList<LinkEntity>();
        jpaLinkRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public LinkEntity saveShortPath(LinkEntity linkEntity) {
        return jpaLinkRepository.save(linkEntity);
    }
}
