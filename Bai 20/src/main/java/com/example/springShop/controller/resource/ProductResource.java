package com.example.springShop.controller.resource;

import com.example.springShop.dto.ProducResponseDto;
import com.example.springShop.dto.ProductRequestDto;
import com.example.springShop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductResource {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProducResponseDto>> getAllProducts(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "categoryId", required = false) Integer categoryId,
            @RequestParam(name = "createdBy" , required = false) String createdBy,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "8") int size
    ) {
        Pageable pageable = PageRequest.of(page,size, Sort.by("id").descending());
        return ResponseEntity.ok(productService.getAllProducts2(keyword,categoryId,createdBy,pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProducResponseDto> getProductById(@PathVariable Integer id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProducResponseDto> createProduct(@RequestBody ProductRequestDto requestDto){
        return ResponseEntity.ok(productService.createProduct(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProducResponseDto> updateProduct(
            @PathVariable Integer id,
            @RequestBody ProductRequestDto requestDto
    ){
        return ResponseEntity.ok(productService.updateProduct(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
