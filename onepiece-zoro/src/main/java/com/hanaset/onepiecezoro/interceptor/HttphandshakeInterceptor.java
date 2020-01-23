package com.hanaset.onepiecezoro.interceptor;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

import static netscape.security.Privilege.SESSION;
import static org.springframework.messaging.simp.stomp.StompHeaders.SESSION;


public class HttphandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler webSocketHandler,
                                   Map attributes) {
        if(request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest) request;
            HttpSession session = serverHttpRequest.getServletRequest().getSession();
            attributes.put(SESSION, session);
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler webSocketHandler,
                                  Exception ex) {

    }
}
