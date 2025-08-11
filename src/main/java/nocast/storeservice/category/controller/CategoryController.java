package nocast.storeservice.category.controller;

import lombok.RequiredArgsConstructor;
import nocast.storeservice.category.dto.CategoryFilter;
import nocast.storeservice.category.dto.CategoryReadDto;
import nocast.storeservice.category.dto.TreeViewOptions;
import nocast.storeservice.category.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.awt.print.Pageable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
@Validated
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<Page<CategoryReadDto>> findAll() {
       return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/")
    public ResponseEntity<Page<CategoryReadDto>> findAll(@RequestBody TreeViewOptions options) {
        return ResponseEntity.ok(categoryService.findAll(options));
    }

    @GetMapping("/")
    public ResponseEntity<Page<CategoryReadDto>> findAll(@RequestBody TreeViewOptions options, @PositiveOrZero @RequestParam Integer page,@PositiveOrZero @RequestParam Integer size) {
        return ResponseEntity.ok(categoryService.findAll(options, PageRequest.of(page, size)));
    }

    @GetMapping("/filter/")
    public ResponseEntity<Page<CategoryReadDto>> findAll(@RequestBody TreeViewOptions options, @PositiveOrZero @RequestParam Integer page, @PositiveOrZero @RequestParam Integer size, CategoryFilter filter) {
        return ResponseEntity.ok(categoryService.findAll(options, PageRequest.of(page, size), filter));
    }
}
