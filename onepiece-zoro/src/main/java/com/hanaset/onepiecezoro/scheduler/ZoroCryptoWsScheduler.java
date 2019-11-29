package com.hanaset.onepiecezoro.scheduler;

import com.hanaset.onepiecezoro.client.websocket.bitcoin.ZoroBTCWebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty(prefix = "cryptows.scheduler", name = "enabled", havingValue = "true")
public class ZoroCryptoWsScheduler {

    private final ZoroBTCWebSocketService zoroBTCWebSocketService;

    public ZoroCryptoWsScheduler(ZoroBTCWebSocketService zoroBTCWebSocketService) {
        this.zoroBTCWebSocketService = zoroBTCWebSocketService;
    }


    @Scheduled(cron = "0 5 * * * *", zone = "Asia/Seoul")
    public void connectBTC() {
        zoroBTCWebSocketService.websockBTCDisconnect();
        zoroBTCWebSocketService.websockBTCConnect();
    }
}
