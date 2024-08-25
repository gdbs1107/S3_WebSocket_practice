package com.example.s3.chatting.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequestDto {

    private String type;
    private Long roomId;
    private Long userId;
    private String message;

    // constructors, getters
}