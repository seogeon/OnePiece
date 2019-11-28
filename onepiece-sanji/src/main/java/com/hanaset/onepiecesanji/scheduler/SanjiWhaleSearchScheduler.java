package com.hanaset.onepiecesanji.scheduler;

import com.hanaset.onepiecesanji.service.SanjiWhaleAlertService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty(prefix = "whale.scheduler", name = "enabled", havingValue = "true")
public class SanjiWhaleSearchScheduler {

    private final SanjiWhaleAlertService sanjiWhaleAlertService;

    public SanjiWhaleSearchScheduler(SanjiWhaleAlertService sanjiWhaleAlertService) {
        this.sanjiWhaleAlertService = sanjiWhaleAlertService;
    }

    @Scheduled(cron = "0 0 * * * *", zone = "Asia/Seoul")
    public void searchWhaleAlert() {
        sanjiWhaleAlertService.searchTransactions();
    }
}
