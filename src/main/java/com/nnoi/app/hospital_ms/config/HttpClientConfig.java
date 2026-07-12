package com.nnoi.app.hospital_ms.config;

import com.nnoi.app.hospital_ms.restclient.RestfulApiDevClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration
@ImportHttpServices(basePackages = "com.nnoi.app.hospital_ms.restclient", types = {RestfulApiDevClient.class})
public class HttpClientConfig {
}
