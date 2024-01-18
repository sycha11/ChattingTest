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

//    @PostMapping
//    public ChatRoomDto createRoom(@RequestParam(value = "name") String name) {
//        System.out.println(name);
//        return chatService.createRoom(name);
//    }

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