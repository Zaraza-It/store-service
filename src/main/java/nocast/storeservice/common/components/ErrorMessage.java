package nocast.storeservice.common.components;

import jakarta.annotation.security.DenyAll;
import lombok.*;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    ERROR_LANG_CODE_UNDEFINED("Error, the language code is not defined"),
    ERROR_CATEGORY_NOT_FOUND_EXCEPTION("Category not found"),
    ERROR_ITEM_NOT_ADDED_TO_CART("Item not added to Cart"),
    ERROR_PRODUCT_NOT_FOUND_EXCEPTION("Product not found"),
    ERROR_PRODUCT_NOT_CREATED("Product not created"),
    ERROR_PRODUCT_FAILED_EDIT_EXCEPTION("Product edit failed"),
    ERROR_USER_NOT_FOUND_EXCEPTION("user not found"),
    ERROR_DELETE_ITEM_FROM_CART_EXCEPTION("Delete item from cart failed");

    private final String message;

}
