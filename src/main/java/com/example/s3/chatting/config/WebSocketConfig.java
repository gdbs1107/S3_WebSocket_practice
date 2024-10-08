package com.example.s3.chatting.config;

import com.example.s3.chatting.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final RoomRepository roomRepository;



    //chat주소로 들어오면 핸들러가 작동 할 수 있도록, 그 밑은 CORS
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat/rooms/*")
                .setAllowedOrigins("/chat/rooms/*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/subscription");
        registry.setApplicationDestinationPrefixes("/publication");
    }
}