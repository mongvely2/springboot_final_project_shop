package com.example.shop.controller;

import com.example.shop.dto.CartDTO;
import com.example.shop.service.CartService;
import com.example.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final MemberService memberService;

    @PostMapping("/add")
    public @ResponseBody String addCart(@ModelAttribute CartDTO cartDTO,
                                        HttpServletRequest request) {
//        Long id = Long.valueOf(1);
//        cartDTO.setId(id);
        System.out.println("cartDTO controller = " + cartDTO);
//        로그인 여부 체크 = 로그인 안했으면 5 반환
//        장바구니는 로그인 회원만 사용 가능하기 때문에 접근 방지
        HttpSession session = request.getSession();
        System.out.println("session = " + session);
        if (session.getAttribute("loginEmail") == null) {
            return "5";
        }
        int result = cartService.addCart(cartDTO);

//        중복아닌 장바구니 DB저장: 1 / 장바구니 중복: 2 / 로그인 안 한 경우: 5 -> 각각 반환
//        addCart 리턴타입 int여서 String 변환을 위해 빈("") 문자열을 더하기 연산함
        return result+"";
    }

}
