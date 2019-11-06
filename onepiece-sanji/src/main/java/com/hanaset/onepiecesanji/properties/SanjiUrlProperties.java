package com.hanaset.onepiecesanji.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@EnableConfigurationProperties
@PropertySource("classpath:properties/url-${spring.profiles.active}.properties")
public class SanjiUrlProperties {

    @Value("${upbit.eventUrl}")
    private String upbitEventUrl;

    @Value("${bithumb.eventUrl}")
    private String bithumbEventUrl;

    @Value("${gdac.eventUrl}")
    private String gdacEventUrl;
}
