package nocast.storeservice.admin.controller;

import lombok.RequiredArgsConstructor;
import nocast.storeservice.exception.UserNotFoundException;
import nocast.storeservice.user.dto.UserFilter;
import nocast.storeservice.user.dto.UserReadDto;
import nocast.storeservice.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.awt.print.Pageable;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/user")
@Validated
public class AdminUserController {
    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<Page<UserReadDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/")
    public ResponseEntity<Page<UserReadDto>> findAll(@PositiveOrZero @RequestParam int size, @PositiveOrZero int page, UserFilter userFilter) {
        return ResponseEntity.ok(userService.findAll(PageRequest.of(page, size), userFilter));
    }

    @GetMapping("/{id]")
    public ResponseEntity<UserReadDto> findById(@Positive @PathVariable long id) {
        final var user = userService.findById(id);
        return Optional.of(user.get())
                .map(ResponseEntity::ok)
                .orElseThrow(UserNotFoundException::new);
    }
}
