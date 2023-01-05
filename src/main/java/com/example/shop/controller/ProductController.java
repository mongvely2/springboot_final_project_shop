package com.example.shop.controller;

import com.example.shop.dto.CategoryDTO;
import com.example.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/category")
    public String categoryForm() {
        return "productPages/categorySave";
    }

//    @PostMapping("/category")
//    public ResponseEntity categorySave(@RequestBody CategoryDTO categoryDTO,
//                                     Model model) {
//        List<CategoryDTO> categoryDTOList = productService.categorySave(categoryDTO);
////        model.addAttribute("categoryList", categoryDTOList);
//        System.out.println("categoryDTOList = " + categoryDTOList);
//        return new ResponseEntity<>(categoryDTOList, HttpStatus.OK);
//    }

//    @PostMapping("/category")
//    public String categorySave(@ModelAttribute CategoryDTO categoryDTO,
//                               Model model) {
//        List<CategoryDTO> categoryDTOList = productService.categorySave(categoryDTO);
//        model.addAttribute("categoryList", categoryDTOList);
//        return "redirect: memberPages/adminPage";
//    }

    @PostMapping("/category")
    public String categorySave(@ModelAttribute CategoryDTO categoryDTO,
                               Model model) {
        productService.categorySave(categoryDTO);
        List<CategoryDTO> categoryDTOList = productService.categoryList();
        model.addAttribute("categoryList", categoryDTOList);
        System.out.println("categoryDTOList = " + categoryDTOList);
        return "redirect: memberPages/adminPage";
    }

    @GetMapping("/save")
    public String saveForm(Model model) {
        List<CategoryDTO> categoryList = productService.categoryList();
        model.addAttribute("categoryList", categoryList);
        System.out.println("categoryList = " + categoryList);
        return "productPages/productSave";
    }
}
