package com.hanaset.onepiecezoro.client.websocket.bitcoin;

import com.google.common.collect.Lists;
import com.hanaset.onepiecezoro.client.websocket.bitcoin.model.BTCWsMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Service
public class ZoroBTCWebSocketService {

    private final ZoroBTCWebSocketClient zoroBTCWebSocketClient;

    public ZoroBTCWebSocketService(ZoroBTCWebSocketClient zoroBTCWebSocketClient) {
        this.zoroBTCWebSocketClient = zoroBTCWebSocketClient;
    }

    @PostConstruct
    public void websockBTC_connect() {

        log.info("<======================== WebSocket Connecting =======================>");
        List<BTCWsMessage> messageList = Lists.newArrayList();

        messageList.add(BTCWsMessage.builder().op("addr_sub").addr("1NDyJtNTjmwk5xPNhjgAMu4HDHigtobu1s").build());
        messageList.add(BTCWsMessage.builder().op("addr_sub").addr("3F6Nn4vFhkkRQW1fTJXQqeTgYvbVxSUNs6").build());
        messageList.add(BTCWsMessage.builder().op("addr_sub").addr("3ENQbSeu9yABRNFre1hsfqgZB8Bhp4k7x1").build());
        messageList.add(BTCWsMessage.builder().op("addr_sub").addr("1JMgJms7UX3gwo9Fwzj8kxRTzm5vqbPtMC").build());
        messageList.add(BTCWsMessage.builder().op("addr_sub").addr("37k5U5xQJkojFHkHVgrY7Dq7xtJJNSqVid").build());
        zoroBTCWebSocketClient.connect(messageList);
    }


}
