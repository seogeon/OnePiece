package com.hanaset.onepiecezoro.scheduler;

import com.hanaset.onepiecezoro.service.ZoroCacheLoadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
@Component
@ConditionalOnProperty(prefix = "cache.scheduler", name = "enabled", havingValue = "true")
public class ZoroCacheScheduler {

    private ZoroCacheLoadService zoroCacheLoadService;

    public ZoroCacheScheduler(ZoroCacheLoadService zoroCacheLoadService) {
        this.zoroCacheLoadService = zoroCacheLoadService;
    }

    @Scheduled(cron = "0 10 */1 * * *")
    public void loadNotice() {
        zoroCacheLoadService.loadNotice();
        log.info("{} 대이터 Loading", ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
    }

}
