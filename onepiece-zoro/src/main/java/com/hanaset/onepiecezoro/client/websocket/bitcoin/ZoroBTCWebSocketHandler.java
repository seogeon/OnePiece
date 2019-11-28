package com.hanaset.onepiecezoro.client.websocket.bitcoin;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class ZoroBTCWebSocketHandler extends BinaryWebSocketHandler {

    public ZoroBTCWebSocketHandler() {

    }

    @Override
    public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        ByteBuffer byteMessage = message.getPayload();
        CharBuffer charBuffer = StandardCharsets.UTF_8.decode(byteMessage);

//        JSONObject jsonObject = (JSONObject) JSONValue.parse(charBuffer.toString());
//        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println("==============================================");
        System.out.println(charBuffer.toString());
        System.out.println("==============================================");
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message){

        JSONObject jsonObject = (JSONObject) JSONValue.parse(message.getPayload());
        System.out.println("==============================================");
        System.out.println(jsonObject);
        System.out.println("==============================================");
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {
        System.out.println(message);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("Connected to BTC Web Socket Server! Session - " + session);
    }
}
