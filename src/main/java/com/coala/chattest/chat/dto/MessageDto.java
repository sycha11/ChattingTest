package com.coala.chattest.chat.dto;

import com.coala.chattest.chat.domain.ChatMessage;
import lombok.Getter;

@Getter
public class MessageDto {

    private ChatMessage.MessageType type;

    // 방 아이디
    private Long roomId;

    //보내는 사람
    private String sender;
    //내용
    private String message;

    private String date;

}
