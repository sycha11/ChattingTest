package com.coala.chattest.chat.controller;

import com.coala.chattest.chat.application.ChatService;
import com.coala.chattest.chat.dto.ChatRoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;


    // 채팅방 메세지 조회
//    GetMapping("/{roomId}/message")

    // 채팅 전송
//    @PostMapping("/{roomId}/message")

    // 채팅방 목록 조회
//    @GetMapping("/rooms")

    @GetMapping
    public List<ChatRoomDto> findAllRoom() {
        return chatService.findAllRoom();
    }

//    @GetMapping
//    public  List<ChatMessageDto> findAllRoom() {
//        List<ChatMessageDto> allRoom = chatService.findAllRoom();
//        return new List<ChatMessageDto>(allRoom, allRoom.size());
//    }


}