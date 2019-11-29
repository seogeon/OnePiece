package com.hanaset.onepiecezoro.client.websocket.bitcoin;

import com.google.common.collect.Lists;
import com.hanaset.onepiececommon.entity.ExchangeWalletEntity;
import com.hanaset.onepiececommon.repository.ExchangeWalletRepository;
import com.hanaset.onepiecezoro.client.websocket.bitcoin.model.BTCWsMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ZoroBTCWebSocketService {

    private final ZoroBTCWebSocketClient zoroBTCWebSocketClient;
    private final ExchangeWalletRepository exchangeWalletRepository;

    public ZoroBTCWebSocketService(ZoroBTCWebSocketClient zoroBTCWebSocketClient,
                                   ExchangeWalletRepository exchangeWalletRepository) {
        this.zoroBTCWebSocketClient = zoroBTCWebSocketClient;
        this.exchangeWalletRepository = exchangeWalletRepository;
    }

    @PostConstruct
    public void websockBTCConnect() {

        log.info("<======================== WebSocket Connecting =======================>");

        List<BTCWsMessage> messageList = Lists.newArrayList();
        List<ExchangeWalletEntity> exchangeWalletEntities = exchangeWalletRepository.findByBlockchain("bitcoin");
        exchangeWalletEntities.forEach(exchangeWalletEntity ->
            messageList.add(BTCWsMessage.builder().op("addr_sub").addr(exchangeWalletEntity.getAddress()).build())
        );

        Map<String, ExchangeWalletEntity> walletEntityMap = exchangeWalletEntities.stream().collect(Collectors.toMap(t -> t.getAddress(), t -> t));

        zoroBTCWebSocketClient.connect(messageList, walletEntityMap);
    }

    public void websockBTCDisconnect() {
        zoroBTCWebSocketClient.disconnect();
    }


}
