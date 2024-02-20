package com.moxi.mogublog.gateway.config.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

@Configuration
public class RouterFunctionConfiguration
{
    @Autowired
    private HystrixFallbackHandler hystrixFallbackHandler;

    @SuppressWarnings("rawtypes")
    @Bean
    public RouterFunction routerFunction()
    {
        return RouterFunctions
                .route(RequestPredicates.path("/defaultfallback").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                        hystrixFallbackHandler);
    }
}

