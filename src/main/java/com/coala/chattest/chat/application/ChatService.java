package com.coala.chattest.chat.application;

import com.coala.chattest.chat.dto.ChatMessageDto;
import com.coala.chattest.chat.dto.ChatRoomDto;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

public interface ChatService {

    List<ChatMessageDto> findAllRoom();
    ChatRoomDto findRoomById(String roomId);

    ChatRoomDto createRoom(String name);
    void deleteRoom(String roomId);

    <T> void sendMessage(WebSocketSession session, T message);

}
