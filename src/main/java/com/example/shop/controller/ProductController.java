package com.example.shop.controller;

import com.example.shop.dto.CategoryDTO;
import com.example.shop.dto.ProductDTO;
import com.example.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @PostMapping("/save")
    public String save(@ModelAttribute ProductDTO productDTO) throws IOException {
        productService.save(productDTO);
        return "memberPages/adminPage";
    }

    @GetMapping
//    Pageble ???????????? ????????? org.springframework.data.domain ??? ????????????!!!!
//    PageableDefault ??? ???????????? ????????? ???????????? ?????? ???????????? ???????????? ????????? ??? ??????
//    ????????? html(??????)?????? ???????????? ???????????? ??????
    public String paging(@PageableDefault(page = 1)Pageable pageable, //import??? springframework ??? ??? ???(java??? ?????? ??? ???)
                         Model model) {
        Page<ProductDTO> productDTOList = productService.paging(pageable);
        model.addAttribute("productList", productDTOList);
//        start ????????? end ????????? ???????????? -> ??????????????? ??????
//        int nowPage = boardDTOList.getNumber();
        int blockLimit = 3;
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit +1;
        int endPage = ((startPage + blockLimit - 1) < productDTOList.getTotalPages()) ? startPage + blockLimit - 1 : productDTOList.getTotalPages();
//        ??????????????? = if/else ??? ???????????? ????????? ???, ????????? ????????? ?????? ?????????(???????????? ??????x)
        int test = 10;
        int num = (test > 5) ? test : 100;    // ?????? if????????? ?????????
        if (test > 5) {
            num = test;
        } else {
            num = 100;
        }
//        model.addAttribute("pageable", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "productPages/productList";
    }

    @GetMapping("{id}")
//    ???????????? ?????? ????????? ??????????????? ???????????????: @PathVariable -> ????????? ????????? error
    public String findById(@PathVariable Long id,
                           @PageableDefault(page = 1) Pageable pageable,
                           Model model) {
//        ????????? 1??????
        productService.updateHits(id);
        ProductDTO productDTO = productService.findById(id);
        System.out.println("productDTO = " + productDTO);

        model.addAttribute("productDTO", productDTO);
        model.addAttribute("page", pageable.getPageNumber());
        return "productPages/productDetail";
    }


}
