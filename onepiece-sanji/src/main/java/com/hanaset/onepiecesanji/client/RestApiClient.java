package com.hanaset.onepiecesanji.client;

import com.hanaset.onepiecesanji.client.gdac.SanjiGdacRestApiClient;
import com.hanaset.onepiecesanji.client.huobi.SanjiHuobiRestApiClient;
import com.hanaset.onepiecesanji.client.upbit.SanjiUpbitRestApiClient;
import com.hanaset.onepiecesanji.properties.SanjiUrlProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@EqualsAndHashCode(callSuper = false)
public class RestApiClient {

    private final SanjiUrlProperties sanjiUrlProperties;

    public RestApiClient(SanjiUrlProperties sanjiUrlProperties) {
        this.sanjiUrlProperties = sanjiUrlProperties;
    }

    @Bean
    public SanjiUpbitRestApiClient SanjiUpbitRestApiClient() {
        return new SanjiUpbitRestApiClient(sanjiUrlProperties);
    }

    @Bean
    public SanjiGdacRestApiClient SanjiGdacRestApiClient() {
        return new SanjiGdacRestApiClient(sanjiUrlProperties);
    }

    @Bean
    public SanjiHuobiRestApiClient SanjiHuobiRestApiClient() { return new SanjiHuobiRestApiClient(sanjiUrlProperties);}
}
