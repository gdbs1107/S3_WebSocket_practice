package com.example.s3.chatting.sevice;

import com.example.s3.chatting.DTO.MessageResponseDto;
import com.example.s3.chatting.utils.MessageIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class EnterRoomService {


    @Autowired
    private SimpMessagingTemplate template;

    public void enterRoom(String type,
                          Long roomId,
                          Long userId) {
        template.convertAndSend(
                "/subscription/chat/room/" + roomId,
                new MessageResponseDto(
                        MessageIdGenerator.generateId(),
                        type,
                        "사용자 " + userId + " 님이 "
                                + "채팅방 " + roomId + "에 입장하셨습니다."
                )
        );
    }
}