package nocast.storeservice.cart.mapper;

import nocast.storeservice.cart.dto.CartReadDto;
import nocast.storeservice.cart.persistence.Cart;
import nocast.storeservice.cart.persistence.CartItem;
import nocast.storeservice.common.components.Mapper;
import org.springframework.stereotype.Component;

@Component
public class CartReadMapper implements Mapper<CartItem, CartReadDto> {
    @Override
    public CartReadDto map(CartItem object) {
        if (object == null) return null;
        return new CartReadDto(
                object.getId(),
                object.getProductId(),
                object.getProductName(),
                object.getQuantity(),
                object.getPrice(),
                object.getPriceType().toString(),
                object.getTotalPrice(),
                object.getCurrency().toString()
        );
    }
}
