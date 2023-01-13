package com.example.shop.controller;

import com.example.shop.dto.CategoryDTO;
import com.example.shop.dto.ProductDTO;
import com.example.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
//    Pageble 임포트시 반드시 org.springframework.data.domain 로 선택할것!!!!
//    PageableDefault 를 사용했기 때문에 매개변수 없이 기본값을 세팅하여 넘겨줄 수 있음
//    실제로 html(뷰단)에서 넘겨주는 매개변수 없음
    public String paging(@PageableDefault(page = 1)Pageable pageable, //import시 springframework 로 할 것(java로 하면 안 됨)
                         Model model) {
        Page<ProductDTO> productDTOList = productService.paging(pageable);
        model.addAttribute("productList", productDTOList);
//        start 페이지 end 페이지 계산방식 -> 삼항연산자 사용
//        int nowPage = boardDTOList.getNumber();
        int blockLimit = 3;
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit +1;
        int endPage = ((startPage + blockLimit - 1) < productDTOList.getTotalPages()) ? startPage + blockLimit - 1 : productDTOList.getTotalPages();
//        삼항연산자 = if/else 를 간단하게 작성한 것, 아래는 풀이를 위한 코드임(기능구현 상관x)
        int test = 10;
        int num = (test > 5) ? test : 100;    // 아래 if문이랑 똑같음
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

    @GetMapping("/{id}")
//    경로상에 있는 숫자를 전달해주는 어노테이션: @PathVariable -> 문자가 있으면 error
    public String findById(@PathVariable Long id,
                           @PageableDefault(page = 1) Pageable pageable,
                           Model model) {
//        조회수 1증가
        productService.updateHits(id);
        ProductDTO productDTO = productService.findById(id);
        System.out.println("productDTO = " + productDTO);

        model.addAttribute("product", productDTO);
        model.addAttribute("page", pageable.getPageNumber());
        return null;
    }


}
