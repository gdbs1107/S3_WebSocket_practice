package com.example.s3.chatting.sevice;

import com.example.s3.chatting.DTO.MessageResponseDto;
import com.example.s3.chatting.utils.MessageIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Service
public class ConvertAndSendMessageService {

    @Autowired
    private SimpMessagingTemplate template;

    public void convertAndSendMessage(String type,
                                      Long roomId,
                                      Long userId,
                                      String message) {
        template.convertAndSend(
                "/subscription/chat/room/" + roomId,
                new MessageResponseDto(
                        MessageIdGenerator.generateId(),
                        type,
                        "사용자 " + userId + ": " + message
                )
        );
    }
}