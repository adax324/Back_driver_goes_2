package com.drivingschool.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
public class AppUrl {
    @Value("${app.url}")
    private String url;

    public String getUrl() {
        return url;
    }

    public AppUrl setUrl(String url) {
        this.url = url;
        return this;
    }
}
