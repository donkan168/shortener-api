package com.example.shortener.domain.useCase;

@FunctionalInterface
public interface CasoUso<T, R> {

    R execute(T comando);

}