package com.hanaset.onepiecezoro.web;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZoroCryptoMessageHandler {

    @MessageMapping("/hello")
    @SendTo("/topic/roomId")
    public String broadcasting(String message) throws Exception {
        return message;
    }
}
