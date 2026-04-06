package com.folder.controller;

import com.folder.model.Product;
import com.folder.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

  private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listProucts(Model model){
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products",products);
        return "product_list";
    }

    @GetMapping("/{id}")
    public String viewProductDetail(@PathVariable("id") Integer id , Model model){
        Product product = productService.getProductById(id);
        if(product == null){
            return "redirect:/products";
        }
        model.addAttribute("product",product);
        return "product_detail";
    }
}
