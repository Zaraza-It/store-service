package nocast.storeservice.cart.persistence;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import nocast.storeservice.common.components.Currency;
import nocast.storeservice.common.components.PriceType;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Table(name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private Long productId;

    @Builder.Default
    private Integer sortOrder = 0;

    private Integer quantity;

    private BigDecimal price;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private PriceType priceType;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public BigDecimal getTotalPrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
