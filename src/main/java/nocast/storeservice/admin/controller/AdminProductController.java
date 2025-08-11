package nocast.storeservice.admin.controller;

import lombok.RequiredArgsConstructor;
import nocast.storeservice.exception.ProductEditFailedException;
import nocast.storeservice.exception.ProductNotCreatedException;
import nocast.storeservice.product.dto.ProductCreateDto;
import nocast.storeservice.product.dto.ProductEditDto;
import nocast.storeservice.product.dto.ProductReadDto;
import nocast.storeservice.product.service.ProductManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/product")
public class AdminProductController {
    private final ProductManagementService productManagementService;

    @PostMapping("/create")
    public ResponseEntity<ProductReadDto> create(@RequestBody ProductCreateDto dto) {
        final var product = productManagementService.create(dto);
        return Optional.of(product.get())
                .map(ResponseEntity::ok)
                .orElseThrow(ProductNotCreatedException::new);
    }

    @PutMapping("/update")
    public ResponseEntity<ProductReadDto> update(@RequestBody ProductEditDto dto, @RequestParam Long id, @RequestHeader boolean active) {
        final var product = productManagementService.update(dto, id, active);
        return Optional.of(product.get())
                .map(ResponseEntity::ok)
                .orElseThrow(ProductEditFailedException::new);
    }

    @DeleteMapping("/delete/")
    public ResponseEntity<Boolean> delete(@RequestParam Long id) {
        return ResponseEntity.ok(productManagementService.delete(id));
    }
}
