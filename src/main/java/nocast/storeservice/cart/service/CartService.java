package nocast.storeservice.cart.service;

import nocast.storeservice.cart.dto.CartAddReadDto;
import nocast.storeservice.cart.dto.CartFilterDto;
import nocast.storeservice.cart.dto.CartReadDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CartService {
    Optional<CartAddReadDto> add(Long userId, Long productId, Integer quantity);

    boolean removeCartItem(Long userId, Long cartItemId);

    Page<CartReadDto> findAll(Long userId);

    Page<CartReadDto> findAll(Long userId, Pageable pageable);

    Page<CartReadDto> findAll(Long userId, Pageable pageable, CartFilterDto dto);

    Optional<CartReadDto> edit(Long userId, Long itemId, Integer quantity);
}
