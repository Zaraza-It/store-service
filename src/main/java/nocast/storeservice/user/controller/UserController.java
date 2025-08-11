package nocast.storeservice.user.controller;

import lombok.RequiredArgsConstructor;
import nocast.storeservice.user.dto.UserCreateDto;
import nocast.storeservice.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Boolean> register(UserCreateDto userCreateDto) {
        return ResponseEntity.ok(userService.register(userCreateDto));
    }
 }
