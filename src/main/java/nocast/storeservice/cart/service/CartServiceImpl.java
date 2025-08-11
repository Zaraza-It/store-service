package nocast.storeservice.cart.service;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import nocast.storeservice.cart.dto.CartAddReadDto;
import nocast.storeservice.cart.dto.CartFilterDto;
import nocast.storeservice.cart.dto.CartReadDto;
import nocast.storeservice.cart.mapper.CartAddMapper;
import nocast.storeservice.cart.mapper.CartEditMapper;
import nocast.storeservice.cart.persistence.Cart;
import nocast.storeservice.cart.persistence.CartItem;
import nocast.storeservice.cart.repository.CartRepository;
import nocast.storeservice.cart.repository.ItemRepository;
import nocast.storeservice.common.components.Mapper;
import nocast.storeservice.exception.CartItemDeletionException;
import nocast.storeservice.product.repository.ProductRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static nocast.storeservice.category.persistence.Category.Fields.id;
import static nocast.storeservice.category.persistence.Category.Fields.sortOrder;
import static org.springframework.data.domain.Sort.by;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final Sort defaultSort = by(sortOrder, id);
    private final Pageable defaultPageable = PageRequest.of(0, 20, defaultSort);
    private final ProductRepository productRepository;
    private final CartAddMapper cartMapper;
    private final Mapper<Cart, CartAddReadDto> cartAddReadDtoMapper;
    private final ItemRepository itemRepository;
    private final Mapper<CartItem, CartReadDto> cartReadDtoMapper;
    private final Mapper<CartFilterDto, Predicate> predicateMapper;
    private final CartEditMapper editMapper;

    @Override
    @Transactional
    public Optional<CartAddReadDto> add(Long userId, Long productId, Integer quantity) {
        final var product = productRepository.findById(productId);
        return Optional.of(product)
                .map(it -> cartMapper.map(it.get(), getCart(userId), quantity))
                .map(cartRepository::save)
                .map(cartAddReadDtoMapper::map);
    }

    @Override
    public boolean removeCartItem(Long userId, Long cartItemId) {
        final var cart = getCart(userId);
        final var itemRemoved = cart.getItems().removeIf(it -> it.getId().equals(cartItemId));
        if (!itemRemoved) {
            throw new CartItemDeletionException();
        }
        cartRepository.saveAndFlush(cart);
        return true;
    }

    @Override
    public Page<CartReadDto> findAll(Long userId) {
        return itemRepository.findAllByCartUserId(userId, defaultPageable)
                .map(cartReadDtoMapper::map);
    }

    @Override
    public Page<CartReadDto> findAll(Long userId, Pageable pageable) {
        return itemRepository.findAllByCartUserId(userId, resolvePageable(pageable))
                .map(cartReadDtoMapper::map);
    }

    @Override
    public Page<CartReadDto> findAll(Long userId, Pageable pageable, CartFilterDto dto) {
        return itemRepository.findAllByCartUserId(userId, resolvePageable(pageable), predicateMapper.map(dto))
                .map(cartReadDtoMapper::map);
    }

    @Override
    public Optional<CartReadDto> edit(Long userId, Long itemId, Integer quantity) {
        final var item = itemRepository.findByIdAndCartUserId(itemId, userId);
        return Optional.ofNullable(item)
                .map(it -> editMapper.map(it.get(), quantity))
                .map(itemRepository::saveAndFlush)
                .map(cartReadDtoMapper::map);
    }

    public Cart getCart(Long userId) {
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        return cart.orElseGet(() -> {
            Cart newCart = Cart.builder()
                    .userId(userId)
                    .build();
            return cartRepository.saveAndFlush(newCart);
        });
    }

    private Pageable resolvePageable(Pageable pageable) {
        if (pageable == null || pageable.isUnpaged()) {
            return defaultPageable;
        }
        Sort sort = pageable.getSort().isUnsorted()
                ? defaultSort
                : pageable.getSort();
        return PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                sort
        );
    }
}
