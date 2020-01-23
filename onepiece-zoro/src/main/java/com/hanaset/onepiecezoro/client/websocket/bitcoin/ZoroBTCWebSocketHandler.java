package com.hanaset.onepiecezoro.client.websocket.bitcoin;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanaset.onepiececommon.entity.ExchangeWalletEntity;
import com.hanaset.onepiecezoro.client.websocket.bitcoin.model.BTCData;
import com.hanaset.onepiecezoro.client.websocket.bitcoin.model.BTCInput;
import com.hanaset.onepiecezoro.client.websocket.bitcoin.model.BTCOut;
import com.hanaset.onepiecezoro.client.websocket.bitcoin.model.BTCResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class ZoroBTCWebSocketHandler extends BinaryWebSocketHandler {

    private BTCData prevData = BTCData.builder().hash("").build();
    private Map<String, ExchangeWalletEntity> walletEntityMap;


    public ZoroBTCWebSocketHandler(Map<String, ExchangeWalletEntity> walletEntityMap) {
        this.walletEntityMap = walletEntityMap;
    }

    @Override
    public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        ByteBuffer byteMessage = message.getPayload();
        CharBuffer charBuffer = StandardCharsets.UTF_8.decode(byteMessage);

        System.out.println("==============================================");
        System.out.println(charBuffer.toString());
        System.out.println("==============================================");
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            BTCResponse response = objectMapper.readValue(message.getPayload(), BTCResponse.class);

            log.info("{}", response);

            BTCData data = response.getX();

            if (!data.getHash().equals(prevData.getHash())) {

                List<BTCInput> inputs = data.getInputs().stream()
                        .filter(btcInput -> btcInput.getPrevOut().getSpent())
                        .filter(btcInput -> {

                            for (String address : walletEntityMap.keySet()) {
                                if (btcInput.getPrevOut().getAddr().equals(address)) {
                                    return true;
                                }
                            }
                            return false;
                        }).collect(Collectors.toList());

                String inputExchange;
                String crypto = "undefined";
                Long value = 0L;

                if (inputs.size() == 0) {
                    inputExchange = "undefined";
                } else {
                    inputExchange = walletEntityMap.get(inputs.get(0).getPrevOut().getAddr()).getExchange();
                    crypto = walletEntityMap.get(inputs.get(0).getPrevOut().getAddr()).getCrypto();
                    value = inputs.stream().map(btcInput -> btcInput.getPrevOut().getValue()).reduce(Long::sum).get();
                }

                List<BTCOut> outs = data.getOut().stream()
                        .filter(btcOut -> btcOut.getSpent())
                        .filter(btcOut -> {

                            for (String address : walletEntityMap.keySet()) {
                                if (btcOut.getAddr().equals(address)) {
                                    return true;
                                }
                            }

                            return false;

                        }).collect(Collectors.toList());

                String outputExchange;

                if (outs.size() == 0) {
                    outputExchange = "undefined";
                } else {

//                    System.out.println(outs.get(0).getAddr());
                    outputExchange = walletEntityMap.get(outs.get(0).getAddr()).getExchange();
                    value = outs.stream().map(btcOut -> btcOut.getValue()).reduce(Long::sum).get();
                    crypto = walletEntityMap.get(outs.get(0).getAddr()).getCrypto();
                }
                log.info("[{}] {} -> {} : {} BTC", crypto.toUpperCase(), inputExchange, outputExchange, BigDecimal.valueOf(value/100000000d).toPlainString()); // 100000000d -> 사토시에서 BTC로 변경
            }

            prevData = data;

        } catch (JsonParseException e) {
            log.error("BTC WebSocket JsonParseException : {}", e.getMessage());
        } catch (JsonMappingException e) {
            log.error("BTC WebSocket JsonMappingException : {}", e.getMessage());
        } catch (IOException e) {
            log.error("BTC WebSocket IOException : {}", e.getMessage());
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("Connected to BTC Web Socket Server! Session - {}", session);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        log.error("TransportError to BTC Web Socket Server! Session - {} => {}", session, exception.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.error("ConnectionClosed to BTC Web Socket Server! Session - {} => {}" + session, status);
    }

}
