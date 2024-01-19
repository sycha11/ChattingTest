package com.coala.chattest.chat.controller;

import com.coala.chattest.chat.application.ChatService;
import com.coala.chattest.chat.dto.ChatRoomDto;
import com.coala.chattest.chat.dto.MakeRoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatRoomController {

    private ChatService chatService;

    // 채팅방 생성
    @PostMapping("/room")
    public ResponseEntity<?> createRoom(@RequestBody MakeRoomDto makeRoomDto){
        System.out.println(makeRoomDto.getSender());
        System.out.println(makeRoomDto.getReceiver());
        return chatService.createRoom(makeRoomDto);
    }

}
