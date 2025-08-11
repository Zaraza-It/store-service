package nocast.storeservice.cart.mapper;

import nocast.storeservice.cart.dto.CartAddReadDto;
import nocast.storeservice.cart.persistence.Cart;
import nocast.storeservice.common.components.Mapper;
import org.springframework.stereotype.Component;

@Component
public class CartAddReadMapper implements Mapper<Cart, CartAddReadDto> {
    @Override
    public CartAddReadDto map(Cart object) {
        final var item = object.getItems().getFirst();
        return new CartAddReadDto(
                item.getProductName(),
                item.getQuantity(),
                item.getPrice(),
                item.getPriceType().toString(),
                item.getTotalPrice(),
                item.getCurrency().toString()
        );
    }
}
