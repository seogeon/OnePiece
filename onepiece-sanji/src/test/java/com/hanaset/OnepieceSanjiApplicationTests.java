package com.hanaset;

import com.hanaset.onepiecesanji.client.RestApiClient;
import com.hanaset.onepiecesanji.service.SanjiBithumbService;
import com.hanaset.onepiecesanji.properties.SanjiUrlProperties;
import com.hanaset.onepiecesanji.service.SanjiGdacService;
import com.hanaset.onepiecesanji.service.SanjiUpbitService;
import com.hanaset.onepiececommon.repository.NoticeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("local")
@SpringBootConfiguration
@SpringBootApplication
@ContextConfiguration(classes = {
        NoticeRepository.class,
        RestApiClient.class,
        SanjiUrlProperties.class
})
public class OnepieceSanjiApplicationTests {

    @Autowired
    private SanjiUpbitService sanjiUpbitService;

    @Autowired
    private SanjiBithumbService sanjiBithumbParser;

    @Autowired
    private SanjiGdacService sanjiGdacService;

    @Test
    public void upbit() {
        System.out.println("=========================================");
        sanjiUpbitService.searchUpbit();
        System.out.println("=========================================");
    }

    @Test
    public void bithumb() {
        System.out.println("=========================================");
       // sanjiBithumbParser.searchBithumbEvent();
        sanjiBithumbParser.searchBithumbNotice();
        System.out.println("=========================================");
    }

    @Test
    public void gdac() {
        System.out.println("=========================================");
        sanjiGdacService.searchGdacEvent();
        System.out.println("=========================================");
    }

}
