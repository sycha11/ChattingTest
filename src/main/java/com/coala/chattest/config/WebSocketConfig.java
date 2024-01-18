package com.coala.chattest.config;
import com.coala.chattest.utils.SocketTextHandler;
import lombok.RequiredArgsConstructor;


import com.coala.chattest.interceptor.ChattingHandshakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

@RequiredArgsConstructor
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer { // Spring 웹 서버에서 WebSocket을 사용하도록 Configuration을 추가해주는 작업을 진행했다.

    @Autowired
    private final SocketTextHandler socketTextHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        /*
         * 스프링에서 웹소켓을 사용하기 위해서 클라이언트가 보내는 통신을 처리할 핸들러가 필요하다
         * 직접 구현한 웹소켓 핸들러 (webSocketHandler)를 웹소켓이 연결될 때, Handshake 할 주소 (/ws/chat)와 함께 addHandler 메소드의 인자로 넣어준다.
         */
        registry.addHandler(socketTextHandler, "/chat/rooms/*")
                .addInterceptors(handshakeInterceptor())
                .setAllowedOrigins("*");
    }

    @Bean
    public HandshakeInterceptor handshakeInterceptor() {
        return new ChattingHandshakeInterceptor();
    }
}