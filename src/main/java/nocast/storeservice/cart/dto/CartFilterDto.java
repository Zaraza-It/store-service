package nocast.storeservice.cart.dto;

import lombok.Value;
import nocast.storeservice.common.components.PriceType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
public class CartFilterDto {
    String productName;
    Integer quantity;
    PriceType priceType;
    BigDecimal minPrice;
    BigDecimal maxPrice;
    LocalDateTime beforeUpdateAt;
    LocalDateTime afterUpdateAt;
}
