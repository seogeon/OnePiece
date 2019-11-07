package com.hanaset.onepiecezoro.scheduler;

import com.hanaset.onepiecezoro.service.ZoroCacheLoadService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
    }
}
