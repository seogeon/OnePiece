package com.hanaset.onepiecesanji.scheduler;

import com.hanaset.onepiecesanji.service.SanjiBithumbService;
import com.hanaset.onepiecesanji.service.SanjiUpbitService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "notice.scheduler", name = "enabled", havingValue = "true")
public class SanjiNoticeSearchScheduler {

    private final SanjiUpbitService sanjiUpbitService;
    private final SanjiBithumbService sanjiBithumbService;

    public SanjiNoticeSearchScheduler(SanjiUpbitService sanjiUpbitService,
                                      SanjiBithumbService sanjiBithumbService) {
        this.sanjiUpbitService = sanjiUpbitService;
        this.sanjiBithumbService = sanjiBithumbService;
    }

    @Scheduled(cron = "0 0 */1 * * *", zone = "Asia/Seoul")
    public void searchNotice() {
        sanjiUpbitService.searchUpbit();
        sanjiBithumbService.searchBithumbEvent();
        sanjiBithumbService.searchBithumbNotice();
    }
}
