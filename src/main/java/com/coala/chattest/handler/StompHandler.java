package com.coala.chattest.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class StompHandler implements ChannelInterceptor {

//    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message); // 헤더의 세션 정보 가져옴?
        System.out.println("startHandler : " + accessor);
        if(accessor.getCommand() == StompCommand.CONNECT) {
            System.out.println("stompStart....");
//            if(!jwtTokenProvider.validateToken(accessor.getFirstNativeHeader("token")))
//                try {
//                    throw new AccessDeniedException("");
//                } catch (AccessDeniedException e) {
//                    e.printStackTrace();
//                }
//            List<String> authorization = accessor.getNativeHeader("Authorization");
//            System.out.println(authorization);

        }

        return message;
    }

}
