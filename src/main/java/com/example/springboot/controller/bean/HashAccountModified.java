package com.example.springboot.controller.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
public class HashAccountModified {

    @Bean
    @RequestScope
    public HashAccount requestScopedHashAccountModified() {
        return new HashAccount();
    }


}
