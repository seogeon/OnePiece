package com.hanaset.onepiecesanji.scheduler;

import com.hanaset.onepiecesanji.service.SanjiBithumbService;
import com.hanaset.onepiecesanji.service.SanjiGdacService;
import com.hanaset.onepiecesanji.service.SanjiOkexService;
import com.hanaset.onepiecesanji.service.SanjiUpbitService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@ConditionalOnProperty(prefix = "notice.scheduler", name = "enabled", havingValue = "true")
public class SanjiNoticeSearchScheduler {

    private final SanjiUpbitService sanjiUpbitService;
    private final SanjiBithumbService sanjiBithumbService;
    private final SanjiGdacService sanjiGdacService;
    private final SanjiOkexService sanjiOkexService;

    public SanjiNoticeSearchScheduler(SanjiUpbitService sanjiUpbitService,
                                      SanjiBithumbService sanjiBithumbService,
                                      SanjiGdacService sanjiGdacService,
                                      SanjiOkexService sanjiOkexService) {
        this.sanjiUpbitService = sanjiUpbitService;
        this.sanjiBithumbService = sanjiBithumbService;
        this.sanjiGdacService = sanjiGdacService;
        this.sanjiOkexService = sanjiOkexService;
    }

    @PostConstruct
    @Scheduled(cron = "0 0 */1 * * *", zone = "Asia/Seoul")
    public void searchNotice() {
        sanjiUpbitService.searchUpbit();
        sanjiBithumbService.searchBithumbEvent();
        sanjiBithumbService.searchBithumbNotice();
        sanjiGdacService.searchGdacEvent();
        sanjiOkexService.searchOkexEvent();
    }

//    @Scheduled(fixedDelay = 1000 * 60)
//    public void delayNotice() {
//        sanjiUpbitService.searchUpbit();
//        sanjiBithumbService.searchBithumbEvent();
//        sanjiBithumbService.searchBithumbNotice();
//        sanjiGdacService.searchGdacEvent();
//        sanjiOkexService.searchOkexEvent();
//    }
}
