package com.example.s3.chatting;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@RequiredArgsConstructor
public class SocketTextHandler extends TextWebSocketHandler {


    private final RoomRepository roomRepository;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        Long roomId = getRoomId(session);

        roomRepository.room(roomId).getSessions().add(session);

        System.out.println("새 클라이언트와 연결되었습니다.");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session,
                                     TextMessage message) throws IOException {
        Long roomId = getRoomId(session);

        Room room = roomRepository.room(roomId);

        System.out.println(message.getPayload());

        for (WebSocketSession connectedSession : room.getSessions()) {
            connectedSession.sendMessage(message);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session,
                                      CloseStatus status) {
        Long roomId = getRoomId(session);

        roomRepository.room(roomId).getSessions().remove(session);

        System.out.println("특정 클라이언트와의 연결이 해제되었습니다.");
    }

    private Long getRoomId(WebSocketSession session) {
        return Long.parseLong(
                session.getAttributes()
                        .get("roomId")
                        .toString()
        );
    }
}