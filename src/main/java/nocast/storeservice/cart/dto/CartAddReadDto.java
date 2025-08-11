package nocast.storeservice.cart.dto;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class CartAddReadDto {
    String productName;
    Integer quantity;
    BigDecimal price;
    String priceType;
    BigDecimal totalPrice;
    String currency;
}
