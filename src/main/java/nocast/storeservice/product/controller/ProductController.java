package nocast.storeservice.product.controller;

import lombok.RequiredArgsConstructor;
import nocast.storeservice.exception.ProductNotFoundException;
import nocast.storeservice.product.dto.ProductFilter;
import nocast.storeservice.product.dto.ProductReadDto;
import nocast.storeservice.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Validated
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public ResponseEntity<Page<ProductReadDto>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/find/")
    public ResponseEntity<Page<ProductReadDto>> findAll(@PositiveOrZero @RequestParam int page, @PositiveOrZero @RequestParam int size) {
        return ResponseEntity.ok(productService.findAll(PageRequest.of(page, size)));
    }

    @GetMapping("/find/")
    public ResponseEntity<Page<ProductReadDto>> findAll(@PositiveOrZero @RequestParam int page, @PositiveOrZero @RequestParam int size, @RequestBody ProductFilter filter) {
        return ResponseEntity.ok(productService.findAll(PageRequest.of(page, size), filter));
    }

    @GetMapping("/id/")
    public ResponseEntity<ProductReadDto> findById(@Positive @RequestParam Long id) {
        final var product = productService.findById(id);
        return Optional.of(product.get())
                .map(ResponseEntity::ok)
                .orElseThrow(ProductNotFoundException::new);
    }
}
