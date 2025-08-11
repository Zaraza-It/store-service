package nocast.storeservice.cart.controller;

import lombok.RequiredArgsConstructor;
import nocast.storeservice.cart.dto.CartAddReadDto;
import nocast.storeservice.cart.dto.CartFilterDto;
import nocast.storeservice.cart.dto.CartReadDto;
import nocast.storeservice.cart.service.CartService;
import nocast.storeservice.exception.CartItemNotAddedException;
import nocast.storeservice.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.awt.print.Pageable;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@Validated
public class CartController {
    private final CartService cartService;
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<CartAddReadDto> add(@RequestHeader Long userId, @Positive Long productId, @Positive Integer quantity) {
        final var item = cartService.add(userId, productId, quantity);
        return Optional.of(item.get())
                .map(ResponseEntity::ok)
                .orElseThrow(CartItemNotAddedException::new);
    }

    @DeleteMapping("/remove")
    public boolean remove(@RequestHeader Long userId, @Positive Long itemId) {
        final var user = userService.getUser();
        return cartService.removeCartItem(user.getId(), itemId);
    }

    @GetMapping("/find")
    public ResponseEntity<Page<CartReadDto>> findAll() {
        final var user = userService.getUser();
        return ResponseEntity.ok(cartService.findAll(user.getId()));
    }

    @GetMapping("/find/")
    public ResponseEntity<Page<CartReadDto>> findAll(@PositiveOrZero @RequestParam int page, @PositiveOrZero @RequestParam int size) {
        final var user = userService.getUser();
        return ResponseEntity.ok(cartService.findAll(user.getId(), PageRequest.of(page, size)));
    }

    @GetMapping("/find/")
    public ResponseEntity<Page<CartReadDto>> findAll(@PositiveOrZero @RequestParam int page, @PositiveOrZero @RequestParam int size, @RequestBody CartFilterDto dto) {
        final var user = userService.getUser();
        return ResponseEntity.ok(cartService.findAll(user.getId(), PageRequest.of(page, size), dto));
    }
}
