package com.coala.chattest.chat.application;

import com.coala.chattest.chat.dto.ChatMessageDto;
import com.coala.chattest.chat.dto.ChatRoomDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatServiceImpl implements ChatService {
    private final ObjectMapper objectMapper;
    private Map<String, ChatRoomDto> chatRooms;

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    //활성화된 모든 채팅방을 조회
    public List<ChatMessageDto> findAllRoom() {
        List<ChatMessageDto> collect = chatRooms.values().stream().map(chatRoomDto ->
                new ChatMessageDto(chatRoomDto.getRoomId(), chatRoomDto.getName(), (long) chatRoomDto.getSessions().size())).collect(Collectors.toList());
        return collect;
    }
    //채팅방 하나를 조회
    public ChatRoomDto findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    //새로운 방 생성
    public ChatRoomDto createRoom(String name) {
        String randomId = UUID.randomUUID().toString();
        ChatRoomDto chatRoomdto = ChatRoomDto.builder()
                .roomId(randomId)
                .name(name)
                .build();
        chatRooms.put(randomId, chatRoomdto);
        return chatRoomdto;
    }
    //방 삭제
    public void deleteRoom(String roomId) {
        ChatRoomDto chatRoomDto = findRoomById(roomId);
        //해당방에 아무도 없다면 자동 삭제
        if(chatRoomDto.getSessions().size() == 0) chatRooms.remove(roomId);
    }

    public <T> void sendMessage(WebSocketSession session, T message){
        try{
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (Exception e){
            e.getMessage();
        }
    }
}
