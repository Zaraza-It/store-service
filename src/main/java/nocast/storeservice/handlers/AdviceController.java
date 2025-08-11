package nocast.storeservice.handlers;

import nocast.storeservice.common.components.ErrorMessage;
import nocast.storeservice.exception.*;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(LangCodeUndefinedException.class)
    public Map<String, String> langCodeUndefined() {
        return Map.of("error", ErrorMessage.ERROR_LANG_CODE_UNDEFINED.toString());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CategoryNotFoundException.class)
    public Map<String, String> categoryNotFound() {
        return Map.of("error", ErrorMessage.ERROR_CATEGORY_NOT_FOUND_EXCEPTION.toString());
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(CartItemNotAddedException.class)
    public Map<String, String> itemNotAddedToCart() {
        return Map.of("error", ErrorMessage.ERROR_ITEM_NOT_ADDED_TO_CART.toString());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductNotFoundException.class)
    public Map<String, String> productNotFound() {
        return Map.of("error", ErrorMessage.ERROR_PRODUCT_NOT_FOUND_EXCEPTION.toString());
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(ProductNotCreatedException.class)
    public Map<String, String> productNotCreated() {
        return Map.of("error", ErrorMessage.ERROR_PRODUCT_NOT_CREATED.toString());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductEditFailedException.class)
    public Map<String, String> productEditFailed() {
        return Map.of("error", ErrorMessage.ERROR_PRODUCT_FAILED_EDIT_EXCEPTION.toString());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> userNotFound() {
        return Map.of("error", ErrorMessage.ERROR_USER_NOT_FOUND_EXCEPTION.toString());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> errorValidation(ConstraintViolationException e) {
        return Map.of("error", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CartItemDeletionException.class)
    public Map<String, String> cartDeleteException() {
        return Map.of("error", ErrorMessage.ERROR_DELETE_ITEM_FROM_CART_EXCEPTION.getMessage());
    }
}
