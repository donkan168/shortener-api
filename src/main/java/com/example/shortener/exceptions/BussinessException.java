package com.example.shortener.exceptions;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.InputStreamResource;

import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

@Getter
public class BussinessException extends RuntimeException{

    private static final String NOMBRE_ARCHIVO_PROPIEDADES_YAML = "application.yml";
    private static final Integer ESTADO_HTTP_POR_DEFECTO = 500;
    private static final String CODIGO_EXCEPCION_POR_DEFECTO = "500";
    private static final String MENSAJE_POR_DEFECTO = "Unexpected error";

    @Getter(AccessLevel.NONE)
    private Properties properties;

    private Integer httpStatusCode;
    private String exceptionCode;

    @Getter(AccessLevel.NONE)
    private String message;

    public BussinessException(String property, Object... messageParameters) {
        super();

        initProperties();

        this.httpStatusCode = Optional.ofNullable(properties.getProperty(String.format("%s.http-code", property)))
                .map(Integer::parseInt).orElse(ESTADO_HTTP_POR_DEFECTO);

        this.exceptionCode = Optional.ofNullable(properties.getProperty(String.format("%s.exception-code", property)))
                .orElse(CODIGO_EXCEPCION_POR_DEFECTO);

        this.message = Optional.ofNullable(properties.getProperty(String.format("%s.message", property)))
                .map(msj -> String.format(msj, messageParameters)).orElse(MENSAJE_POR_DEFECTO);
    }

    public void initProperties(){
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(NOMBRE_ARCHIVO_PROPIEDADES_YAML);

        YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
        factory.setResources(new InputStreamResource(inputStream));
        factory.afterPropertiesSet();

        properties = factory.getObject();
    }

    @Override
    public String getMessage(){
        return this.message;
    }
}
