package com.hanaset.onepiecezoro.client.websocket.bitcoin;

import com.hanaset.onepiecezoro.client.websocket.WebSocketConstants;
import com.hanaset.onepiecezoro.client.websocket.bitcoin.model.BTCWsMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@Slf4j
@Component
public class ZoroBTCWebSocketClient {

    private WebSocketSession webSocketSession;

    public void connect(List<BTCWsMessage> messageList) {

        log.info("Connecting to BTC Web Socket Server...");

        try {
            WebSocketClient webSocketClient = new StandardWebSocketClient();

            webSocketSession =
                    webSocketClient.doHandshake(new ZoroBTCWebSocketHandler(), new WebSocketHttpHeaders(), URI.create(WebSocketConstants.BTC_URL)).get();
            try {


                webSocketSession.sendMessage(new TextMessage("{\"op\":\"addr_sub\",\"addr\":\"1NDyJtNTjmwk5xPNhjgAMu4HDHigtobu1s\"}"));
                webSocketSession.sendMessage(new TextMessage("{\"op\":\"addr_sub\",\"addr\":\"3F6Nn4vFhkkRQW1fTJXQqeTgYvbVxSUNs6\"}"));


                log.info("BTC Client successfully sent subscription message");
            } catch (Exception e) {
                log.error("Exception while sending a message", e);
            }

        } catch (Exception e) {
            log.error("Exception while accessing websockets", e);
        }
    }

    public void disconnect() {

        try {
            webSocketSession.close();
            log.info("<======================== WebSocket Closed =======================>");
        } catch (IOException e) {
            log.error("WebSocket Close Error");
        } catch (NullPointerException e) {
            log.error("Not Connect WebSocket");
        }

    }
}
