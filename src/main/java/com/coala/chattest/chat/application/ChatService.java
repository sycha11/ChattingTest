package com.coala.chattest.chat.application;

import com.coala.chattest.chat.dao.ChatRoomRepository;
import com.coala.chattest.chat.domain.ChatRoom;
import com.coala.chattest.chat.dto.ChatRoomDto;
import com.coala.chattest.chat.dto.MakeRoomDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
    private final ObjectMapper objectMapper;
    private ChatRoomRepository chatRoomRepository;

    private Map<String, ChatRoomDto> chatRooms;

    //채팅방 생성
    public ResponseEntity<?> createRoom(MakeRoomDto makeRoomDto) {
        ChatRoom chatRoom = ChatRoom.builder()
                .sender(makeRoomDto.getSender())
                .receiver(makeRoomDto.getReceiver())
                .build();
        System.out.println("service: " + chatRoom.getSender());
        chatRoomRepository.save(chatRoom);
        return ResponseEntity.ok()
                .body(chatRoom);
    }



    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    // 모든 채팅방 찾기
    public List<ChatRoomDto> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoomDto findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

//    public ChatRoomDto createRoom(String name) {
//        String randomId = UUID.randomUUID().toString();
//        ChatRoomDto chatRoomDto = ChatRoomDto.builder()
//                .roomId(randomId)
//                .name(name)
//                .build();
//        chatRooms.put(randomId, chatRoomDto);
//        return chatRoomDto;
//    }




    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
