package nocast.storeservice.cart.dto;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class CartReadDto {
    Long id;
    Long productId;
    String productName;
    Integer quantity;
    BigDecimal price;
    String priceType;
    BigDecimal totalPriceProduct;
    String currency;
}
