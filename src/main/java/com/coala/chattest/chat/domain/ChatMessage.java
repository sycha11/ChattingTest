package com.coala.chattest.chat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public enum MessageType {
        ENTER, TALK
    }

    private MessageType type;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "room_id")
    private ChatRoom chatRoom;

    //보내는 사람
    private String sender;
    //내용
    private String message;

    private String date;

    // 채팅생성
}