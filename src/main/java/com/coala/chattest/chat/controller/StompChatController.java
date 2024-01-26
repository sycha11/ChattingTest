package com.coala.chattest.chat.controller;

import com.coala.chattest.chat.application.ChatService;
import com.coala.chattest.chat.domain.ChatMessage;
import com.coala.chattest.chat.dto.ChatMessageDto;
import com.coala.chattest.chat.dto.MessageDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class StompChatController {

    private final SimpMessagingTemplate template; //특정 Broker 로 메세지를 전달
    private final ChatService chatService;

    @MessageMapping("/chat/{roomId}")
    public void enter(@DestinationVariable(value = "roomId") Long roomId, MessageDto message) {
//        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
//            message.setMessage(message.getSender()+"님이 입장하였습니다.");
//        }

        System.out.println("enter()........");
        log.info("roomID = {}", message.getRoomId());

        chatService.saveMessage(message.getRoomId(), message);
        System.out.println("controller555");
        message.setMessage(message.getSender() + "님이 채팅방에 참여하였습니다.");

        template.convertAndSend("/sub/channel/" + message.getRoomId(), message);
    }

    // /pub/chat/roomId/message 에 메세지가 오면 동작
    @MessageMapping(value = "/chat/{roomId}/message")
    public void message(@DestinationVariable(value = "roomId") Long roomId, MessageDto message){
        chatService.saveMessage(message.getRoomId(), message);

        System.out.println("roomId: " + roomId);
        System.out.println("sender:" + message.getSender());
        System.out.println("message: " + message.getMessage());
        template.convertAndSend("/sub/channel" + roomId, message);
    }
}
