package com.nnoi.app.hospital_ms.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "common.variables")
public class CommonVariables {
    private String greetings;

    public CommonVariables(String greetings) {
        this.greetings = greetings;
    }

    public String getGreetings() {
        return greetings;
    }
}
