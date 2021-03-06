package com.hanaset;

import com.hanaset.onepiececommon.repository.ExchangeWalletRepository;
import com.hanaset.onepiecesanji.client.RestApiClient;
import com.hanaset.onepiecesanji.service.*;
import com.hanaset.onepiecesanji.properties.SanjiUrlProperties;
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
        ExchangeWalletRepository.class,
        SanjiUrlProperties.class
})
public class OnepieceSanjiApplicationTests {

    @Autowired
    private SanjiUpbitService sanjiUpbitService;

    @Autowired
    private SanjiBithumbService sanjiBithumbParser;

    @Autowired
    private SanjiGdacService sanjiGdacService;

    @Autowired
    private SanjiOkexService sanjiOkexService;

    @Autowired
    private SanjiBitsonicService sanjiBitsonicService;

    @Autowired
    private SanjiHuobiService sanjiHuobiService;

    @Autowired
    private SanjiCoinoneService sanjiCoinoneService;

    @Autowired
    private SanjiBinanaceService sanjiBinanaceService;

    @Autowired
    private SanjiBitfinexService sanjiBitfinexService;

    @Autowired
    private SanjiWhaleAlertService sanjiWhaleAlertService;

    @Test
    public void upbit() {
        System.out.println("=========================================");
        sanjiUpbitService.searchUpbit();
        System.out.println("=========================================");
    }

    @Test
    public void bithumb() {
        System.out.println("=========================================");
        //sanjiBithumbParser.searchBithumbEvent();
        sanjiBithumbParser.searchBithumbNotice();
        System.out.println("=========================================");
    }

    @Test
    public void gdac() {
        System.out.println("=========================================");
        sanjiGdacService.searchGdacEvent();
        System.out.println("=========================================");
    }

    @Test
    public void okex() {
        System.out.println("=========================================");
        sanjiOkexService.searchOkexEvent();
//        sanjiOkexService.test();
        System.out.println("=========================================");
    }

    @Test
    public void bitsonic() {
        System.out.println("=========================================");
        sanjiBitsonicService.searchBitsonicEvent();
        sanjiBitsonicService.searchBitsoicNotice();
        System.out.println("=========================================");
    }

    @Test
    public void huobi() {
        System.out.println("=========================================");
        sanjiHuobiService.searchHuobiEvent();
        System.out.println("=========================================");
    }

    @Test
    public void coinone() {
        System.out.println("=========================================");
        sanjiCoinoneService.searchCoinoneEvent();
        System.out.println("=========================================");
    }

    @Test
    public void binance() {
        System.out.println("=========================================");
        sanjiBinanaceService.searchBinanceEvent();
        System.out.println("=========================================");
    }

    @Test
    public void bitfinex() {
        System.out.println("=========================================");
        sanjiBitfinexService.searchBitfinexNotice();
        System.out.println("=========================================");
    }

    @Test
    public void ??????() {
        sanjiUpbitService.searchUpbit();
        sanjiBithumbParser.searchBithumbNotice();
        sanjiGdacService.searchGdacEvent();
        sanjiOkexService.searchOkexEvent();
        sanjiBitsonicService.searchBitsonicEvent();
        sanjiBitsonicService.searchBitsoicNotice();
        sanjiHuobiService.searchHuobiEvent();
        sanjiCoinoneService.searchCoinoneEvent();
        sanjiBinanaceService.searchBinanceEvent();
        sanjiBitfinexService.searchBitfinexNotice();
    }

    @Test
    public void whale() {
        System.out.println("=========================================");
        sanjiWhaleAlertService.searchTransactions();
        System.out.println("=========================================");
    }


}
