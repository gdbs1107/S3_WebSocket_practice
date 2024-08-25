package com.example.s3.chatting.utils;

public class MessageIdGenerator {

    private static Long id = 0L;

    public static Long generateId() {
        id += 1;
        return id;
    }
}
