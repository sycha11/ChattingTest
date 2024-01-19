package com.coala.chattest.config;
import com.coala.chattest.utils.SocketTextHandler;
import lombok.RequiredArgsConstructor;


import com.coala.chattest.interceptor.ChattingHandshakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@RequiredArgsConstructor
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer { // Spring 웹 서버에서 WebSocket을 사용하도록 Configuration을 추가해주는 작업을 진행했다.

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { // 소켓을 연결해주는 uri
        //setAllowedOriginPatterns(”*”) : 소켓 또한 CORS 설정을 해주어야 한다.
        //withSockJS() : 소켓을 지원하지 않는 브라우저라면, sockJS를 사용하도록 설정
        registry.addEndpoint("/stomp/chat")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        registry.setApplicationDestinationPrefixes("/pub");
        registry.enableSimpleBroker("/sub");
    }
}