package com.example.shortener.domain.out;

import com.example.shortener.core.LinkEntity;

import java.util.List;

public interface LinkRepository {

    List<LinkEntity> getPath(String path);

    LinkEntity saveShortPath(LinkEntity linkEntity);
}
