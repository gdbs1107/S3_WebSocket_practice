package com.example.s3.notification;

import com.example.s3.user.User;
import com.example.s3.user.UserDto;
import com.example.s3.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;

    public UserDto authenticate(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return UserDto.fromEntity(user);
    }
}
