package com.example.shortener.exceptions;

public class ShortenerException extends BussinessException{

    private static final String BASE_PROPERTY = "shortener-api.exceptions.shortener";

    public ShortenerException(String propertyCode, Object... messageParamters) {
        super(String.format("%s[%s]", BASE_PROPERTY, propertyCode), messageParamters);
    }
}
