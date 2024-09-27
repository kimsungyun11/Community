package com.ProgrammerCommunity.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MappingsLogger implements CommandLineRunner {

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Override
    public void run(String... args) throws Exception {
        requestMappingHandlerMapping.getHandlerMethods().forEach((key, value) -> 
            log.info("Mapped URL path [{}] onto method [{}]", key, value)
        );
    }
}
