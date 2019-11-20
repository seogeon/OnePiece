package com.hanaset.onepiecesanji.scheduler;

import com.hanaset.onepiecesanji.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
@Component
@ConditionalOnProperty(prefix = "notice.scheduler", name = "enabled", havingValue = "true")
public class SanjiNoticeSearchScheduler {

    private final SanjiUpbitService sanjiUpbitService;
    private final SanjiBithumbService sanjiBithumbService;
    private final SanjiGdacService sanjiGdacService;
    private final SanjiOkexService sanjiOkexService;
    private final SanjiCoinoneService sanjiCoinoneService;
    private final SanjiBitsonicService sanjiBitsonicService;
    private final SanjiHuobiService sanjiHuobiService;

    private final SanjiBinanaceService sanjiBinanaceService;
    private final SanjiBitfinexService sanjiBitfinexService;

    public SanjiNoticeSearchScheduler(SanjiUpbitService sanjiUpbitService,
                                      SanjiBithumbService sanjiBithumbService,
                                      SanjiGdacService sanjiGdacService,
                                      SanjiOkexService sanjiOkexService,
                                      SanjiCoinoneService sanjiCoinoneService,
                                      SanjiBitsonicService sanjiBitsonicService,
                                      SanjiHuobiService sanjiHuobiService,
                                      SanjiBinanaceService sanjiBinanaceService,
                                      SanjiBitfinexService sanjiBitfinexService) {
        this.sanjiUpbitService = sanjiUpbitService;
        this.sanjiBithumbService = sanjiBithumbService;
        this.sanjiGdacService = sanjiGdacService;
        this.sanjiOkexService = sanjiOkexService;
        this.sanjiCoinoneService = sanjiCoinoneService;
        this.sanjiBitsonicService = sanjiBitsonicService;
        this.sanjiHuobiService = sanjiHuobiService;
        this.sanjiBinanaceService = sanjiBinanaceService;
        this.sanjiBitfinexService = sanjiBitfinexService;
    }

    @Scheduled(cron = "0 5 */1 * * *", zone = "Asia/Seoul")
    public void searchNotice() {
        sanjiUpbitService.searchUpbit();
        sanjiBithumbService.searchBithumbNotice();
        sanjiGdacService.searchGdacEvent();
        sanjiOkexService.searchOkexEvent();
        sanjiCoinoneService.searchCoinoneEvent();
        sanjiBitsonicService.searchBitsoicNotice();
        sanjiBitsonicService.searchBitsonicEvent();
        sanjiHuobiService.searchHuobiEvent();

        sanjiBinanaceService.searchBinanceEvent();
        sanjiBitfinexService.searchBitfinexNotice();

        log.info("{} 데이터 Searching", ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
    }

//    @Scheduled(fixedDelay = 1000 * 60)
//    public void delayNotice() {
//        sanjiUpbitService.searchUpbit();
//        sanjiBithumbService.searchBithumbEvent();
//        sanjiBithumbService.searchBithumbNotice();
//        sanjiGdacService.searchGdacEvent();
//        sanjiOkexService.searchOkexEvent();
//
//        log.info("{} 데이터 Searching", ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
//    }
}
