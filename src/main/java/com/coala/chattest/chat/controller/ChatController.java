package com.coala.chattest.chat.controller;

import com.coala.chattest.chat.application.ChatService;
import com.coala.chattest.chat.domain.ChatMessage;
import com.coala.chattest.chat.dto.ChatMessageDto;
import com.coala.chattest.chat.dto.ChatRoomDto;
import com.coala.chattest.chat.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class ChatController {

    private final SimpMessageSendingOperations sendingOperations;

//    @MessageMapping("/chat/message")
//    public void enter(ChatMessageDto message) {
//        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
//            message.setMessage(message.getSender()+"님이 입장하였습니다.");
//        }
//        // 앞에 /topic을 주어 topic을 구독한 사람들에게 메시지를 보냄
//        sendingOperations.convertAndSend("/topic/chat/room/"+message.getRoomId(), message);
//    }



}