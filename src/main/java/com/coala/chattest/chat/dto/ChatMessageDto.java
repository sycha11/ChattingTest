package com.coala.chattest.chat.dto;

import lombok.*;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ChatMessageDto {

    private MessageType type;
    private String roomId;
    private String sender;
    private String message;

    public ChatMessageDto(String roomId, String sender, long size){
        this.roomId = roomId;
        this.sender = sender;
    }

    public enum MessageType {
        ENTER, TALK
    }

}
