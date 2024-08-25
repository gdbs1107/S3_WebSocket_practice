package com.example.s3.chatting;

import com.example.s3.chatting.utils.RoomIdGenerator;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
public class Room {

    private Long id;

    private String name;

    private final Set<WebSocketSession> sessions = new HashSet<>();

    public static Room create(String name) {
        Room room = new Room();
        room.id = RoomIdGenerator.createId();
        room.name = name;
        return room;
    }

}
