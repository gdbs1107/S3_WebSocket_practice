package com.example.s3.notification;

import com.example.s3.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/v1/users/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final AuthenticationService authenticationService;
    private final NotificationService notificationService;

    @GetMapping("/subscribe")
    public SseEmitter subscribe(@RequestParam String username) {
        // username을 통해 UserDto를 얻는다.
        UserDto userDto = authenticationService.authenticate(username);

        // 서비스를 통해 생성된 SseEmitter를 반환
        return notificationService.connectNotification(userDto.getId());
    }
}
