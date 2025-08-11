package nocast.storeservice.cart.service;

import lombok.RequiredArgsConstructor;
import nocast.storeservice.TestcontainersConfiguration;
import nocast.storeservice.cart.persistence.CartItem;
import nocast.storeservice.cart.repository.CartRepository;
import nocast.storeservice.common.components.PriceType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@Sql(scripts = "/test-cart-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/test-product-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Import(TestcontainersConfiguration.class)
@Rollback
@ApplicationModuleTest(ApplicationModuleTest.BootstrapMode.ALL_DEPENDENCIES)
@RequiredArgsConstructor
public class CartServiceTest {
    @Autowired
    private final CartService cartService;
    private final CartRepository cartRepository;

    @Test
    void addCartTest() {
        final var dto = cartService.add(4L, 1L, 2);
        assertThat(dto)
                .isNotNull()
                .isNotEmpty();
        final var result = cartRepository.findById(4L);
        assertThat(result)
                .isPresent()
                .isNotNull()
                .isNotEmpty();
        final var cart = result.get();
        assertThat(cart)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", 4L);
        CartItem item = cart.getItems().getFirst();
        assertThat(item)
                .isNotNull()
                .hasFieldOrPropertyWithValue("productId", 1L)
                .hasFieldOrPropertyWithValue("quantity", 2)
                .hasFieldOrPropertyWithValue("price", BigDecimal.valueOf(699.99));
    }

    @Test
    void removeCartItemTest() {
        final var test = cartRepository.findByUserId(3L);
        System.out.println(test.get().getItems().getFirst().getId());
        boolean info = cartService.removeCartItem(3L, 4L);
        assertThat(info)
                .isTrue();
    }

    @Test
    void findAllTest() {
       final var result = cartService.findAll(3L, PageRequest.of(0, 10));
       result.forEach(System.out::println);
       assertThat(result)
               .hasSize(2);
    }

    @Test
    void findAllDefault() {
        final var result = cartService.findAll(3L);
        result.forEach(System.out::println);
        assertThat(result)
            .hasSize(2)
                .anyMatch(it -> it.getId().equals(4L))
                .anyMatch(it -> it.getId().equals(5L))
                .anyMatch(it -> it.getPriceType().equals(PriceType.PAID.toString()));
    }

    @Test
    void editTest() {
        final var result = cartService.edit(1L, 2L, 3);
        assertThat(result)
                .isNotEmpty()
                .isNotNull();
        final var info = result.get();
        assertThat(info)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", 2L)
                .hasFieldOrPropertyWithValue("quantity", 3);
    }
}
