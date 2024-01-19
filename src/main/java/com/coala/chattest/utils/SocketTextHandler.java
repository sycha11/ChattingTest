package com.coala.chattest.utils;

import com.coala.chattest.chat.dao.RoomRepository;
import com.coala.chattest.chat.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Configuration
//클라이언트가 전송한 텍스트 요청을 처리할 TextWebSocketHandler를 상속받은 socketTextHandler를 작성
//afterConnectionClosed -> 웹 소켓 연결이 종료되고 나서 서버단에서 실행해야할 일들을 정의해주는 메소드
// afterConnectionEstablished -> 연결이 성사 되고 나서 해야할 일들.
// handleTextMessage-> 웹소켓 서버단으로 메세지가 도착했을때 해주어야할 일들을 정의하는 메소드 입니다.

public class SocketTextHandler extends TextWebSocketHandler {

    private final Set<WebSocketSession> sessions = new HashSet<>(); // 유저들의 세션 저장
    @Autowired
    private RoomRepository roomRepository;

    // 채팅 세션 연결
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        Long roomId = getRoomId(session);

        roomRepository.room(roomId).getSessions().add(session);
        System.out.println("SocketTextHandler : afterConnectionEstablished");
        System.out.println("새 클라이언트와 연결되었습니다.");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session,
                                     TextMessage message) throws Exception {
        Long roomId = getRoomId(session);

        Room room = roomRepository.room(roomId);

        System.out.println(message.getPayload());

        for (WebSocketSession connectedSession : room.getSessions()) {
            connectedSession.sendMessage(message);
        }

        System.out.println("SocketTextHandler : handleTextMessage");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session,
                                      CloseStatus status) {
        Long roomId = getRoomId(session);

        roomRepository.room(roomId).getSessions().remove(session);

        System.out.println("특정 클라이언트와의 연결이 해제되었습니다.");
    }

    private Long getRoomId(WebSocketSession session) {
        System.out.println("SocketTextHandler : getRoomId");
//        return Long.parseLong(
//                session.getAttributes()
//                        .get("roomId")
//                        .toString()
//        );
        // uri를 가져와서
        String uri = Objects.requireNonNull(session.getUri())
                .toString();
        String[] parts = uri.split("/");
        String roomId = parts[parts.length - 1];
        return Long.parseLong(roomId);
    }

}
