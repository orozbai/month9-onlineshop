package com.example.market.controller;

import com.example.market.entity.Category;
import com.example.market.entity.Product;
import com.example.market.service.CategoryService;
import com.example.market.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class WebController {
    final private CategoryService categoryService;
    final private ProductService productService;

    @GetMapping("products/")
    public String listProducts(@RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "size", defaultValue = "3") int size,
                               @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
                               @RequestParam(name = "sortOrder", defaultValue = "asc") String sortOrder,
                               @RequestParam(name = "searchName", required = false) String searchName,
                               @RequestParam(name = "categoryId", required = false) Long categoryId,
                               @RequestParam(name = "minPrice", required = false) Integer minPrice,
                               @RequestParam(name = "maxPrice", required = false) Integer maxPrice, Model model) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortOrder.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));
        Page<Product> products;
        if (searchName != null && !searchName.isBlank()) {
            products = productService.findByName(searchName, pageable);
        } else if (categoryId != null) {
            products = productService.findByCategory(categoryId, pageable);
        } else if (minPrice != null && maxPrice != null) {
            products = productService.findByPrice(minPrice, maxPrice, pageable);
        } else {
            products = productService.getThreeProducts(pageable);
        }
        model.addAttribute("products", products.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("totalItems", products.getTotalElements());
        model.addAttribute("searchName", searchName);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);

        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);
        //сделать страничку с формой поиска и таблицей для отображения товаров
        //изменить контроллер чтобы раобтал freemarker
        return "products";
    }

    @GetMapping()
    public String mainPage() {
        return "main";
    }
}
