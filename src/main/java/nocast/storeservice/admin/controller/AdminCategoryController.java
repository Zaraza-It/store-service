package nocast.storeservice.admin.controller;

import lombok.RequiredArgsConstructor;
import nocast.storeservice.category.dto.CategoryCreateDto;
import nocast.storeservice.category.dto.CategoryEditDto;
import nocast.storeservice.category.dto.CategoryReadDto;
import nocast.storeservice.category.service.CategoryManagementService;
import nocast.storeservice.category.service.CategoryService;
import nocast.storeservice.exception.CategoryNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/category")
public class AdminCategoryController {
    private final CategoryService categoryService;
    private final CategoryManagementService categoryManagementService;

    @GetMapping("/search/")
    public ResponseEntity<CategoryReadDto> findCategoryById(@RequestParam int id) {
        final var category = categoryService.findById(id);
        return Optional.of(category.get())
                .map(ResponseEntity::ok)
                .orElseThrow(CategoryNotFoundException::new);
    }

    @PostMapping("/category/create/")
    public ResponseEntity<CategoryReadDto> createCategory(@RequestBody CategoryCreateDto categoryCreateDto) {
        return ResponseEntity.ok(categoryManagementService.create(categoryCreateDto));
    }

    @DeleteMapping("/delete/")
    public boolean deleteCategoryById(@RequestParam int id) {
        return categoryManagementService.delete(id);
    }

    @PutMapping("/update/")
    public ResponseEntity<CategoryReadDto> updateCategory(@RequestBody CategoryEditDto dto) {
        final var category = categoryManagementService.update(dto);
        return Optional.of(category.get())
                .map(ResponseEntity::ok)
                .orElseThrow(CategoryNotFoundException::new);
    }
}
