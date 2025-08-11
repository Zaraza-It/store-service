package nocast.storeservice.cart.mapper;

import nocast.storeservice.cart.persistence.Cart;
import nocast.storeservice.cart.persistence.CartItem;
import nocast.storeservice.product.persistence.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;

@Component
public class CartAddMapper {

    public Cart map(Product object, Cart cart, Integer quantity) {
        if (quantity > object.getStockQuantity() || quantity <= 0) {
            return null;
        }
        CartItem newItem = CartItem.builder()
                .productId(object.getId())
                .priceType(object.getPriceType())
                .quantity(quantity)
                .price(object.getPrice())
                .productName(object.getName())
                .imageUrl(object.getImage())
                .currency(object.getCurrency())
                .sortOrder(object.getSortOrder())
                .totalPrice(object.getPrice().multiply(BigDecimal.valueOf(quantity)))
                .cart(cart)
                .build();
        cart.addItem(newItem);
        return cart;
    }
}
