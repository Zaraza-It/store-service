package nocast.storeservice.cart.mapper;

import nocast.storeservice.cart.persistence.CartItem;
import nocast.storeservice.common.components.Mapper;
import org.springframework.stereotype.Component;

@Component
public class CartEditMapper {

    public CartItem map(CartItem item, Integer quantity) {
        item.setQuantity(quantity);
        return item;
    }
}
