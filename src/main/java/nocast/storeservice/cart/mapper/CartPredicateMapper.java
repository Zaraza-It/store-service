package nocast.storeservice.cart.mapper;

import com.querydsl.core.types.Predicate;
import nocast.storeservice.cart.dto.CartFilterDto;
import nocast.storeservice.cart.persistence.QCart;
import nocast.storeservice.common.components.Mapper;
import nocast.storeservice.common.components.QPredicates;
import org.springframework.stereotype.Component;

import static nocast.storeservice.cart.persistence.QCartItem.cartItem;


@Component
public class CartPredicateMapper implements Mapper<CartFilterDto, Predicate> {
    @Override
    public Predicate map(CartFilterDto object) {
        return QPredicates.builder()
                .add(object.getProductName(), cartItem.productName::contains)
                .add(object.getMaxPrice(), cartItem.price::loe)
                .add(object.getMinPrice(), cartItem.price::goe)
                .add(object.getQuantity(), cartItem.quantity::loe)
                .add(object.getAfterUpdateAt(), cartItem.cart.lastModified::before)
                .add(object.getAfterUpdateAt(), cartItem.cart.lastModified::after)
                .add(object.getPriceType(), cartItem.priceType::eq)
                .build();
    }
}
