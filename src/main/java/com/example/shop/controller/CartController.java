package com.example.shop.controller;

import com.example.shop.dto.CartProductDTO;
import com.example.shop.dto.MemberDTO;
import com.example.shop.service.CartService;
import com.example.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final MemberService memberService;

//    @GetMapping("/saved")
//    public @ResponseBody String cartSave(@ModelAttribute ItemDTO itemDTO , HttpSession session){
//        Object member = session.getAttribute("member");
//        member = (MemberDTO) member;
//        String userId = ((MemberDTO) member).getUserId();
//        itemDTO.setUserId(userId);
//        System.out.println("장바구니저장itemDTO = " + itemDTO);
//        cartService.save(itemDTO);
//        return userId;
//    }

    @PostMapping("/add")
    public @ResponseBody String addCart(@RequestParam Long productId,
                                        @RequestParam int cartCount,
                                        HttpSession session) {
//        로그인 여부 체크 = 로그인 안했으면 5 반환
//        장바구니는 로그인 회원만 사용 가능하기 때문에 접근 방지
        Object loginSession = session.getAttribute("loginSession");
        loginSession = (MemberDTO) loginSession;
        System.out.println("loginSession = " + loginSession);
        if (loginSession == null) {
            return "5";
        }
        Long memberId = ((MemberDTO) loginSession).getId();
        System.out.println("memberId = " + memberId);
        int result = cartService.addCart(productId, cartCount, memberId);

//        중복아닌 장바구니 DB저장: 1 / 장바구니 중복: 2 / 로그인 안 한 경우: 5 -> 각각 반환
//        addCart 리턴타입 int여서 String 변환을 위해 빈("") 문자열을 더하기 연산함
        return result+"";
    }


//    @GetMapping("/cartList/{memberId}")
////    경로상에 있는 숫자를 전달해주는 어노테이션: @PathVariable -> 문자가 있으면 error
//    public String cartList(@PathVariable Long memberId,
//                           Model model) {
//        System.out.println("memberId = " + memberId);
//        List<CartProductDTO> cartDTOList = cartService.findAll(memberId);
//        model.addAttribute("cartList", cartDTOList);
//        System.out.println("cartDTOList = " + cartDTOList);
//        return "cartPages/cartList";
//    }

//    --------------------------------------------


//    @PostMapping("/add")
//    public @ResponseBody String addCart(@ModelAttribute CartProductDTO cartProductDTO,
//                                        HttpServletRequest request) {
//        System.out.println("cartDTO controller = " + cartDTO);
////        로그인 여부 체크 = 로그인 안했으면 5 반환
////        장바구니는 로그인 회원만 사용 가능하기 때문에 접근 방지
//        HttpSession session = request.getSession();
//        System.out.println("session = " + session);
//        if (session.getAttribute("loginSession") == null) {
//            return "5";
//        }
//        int result = cartService.addCart(cartDTO);
//
////        중복아닌 장바구니 DB저장: 1 / 장바구니 중복: 2 / 로그인 안 한 경우: 5 -> 각각 반환
////        addCart 리턴타입 int여서 String 변환을 위해 빈("") 문자열을 더하기 연산함
//        return result+"";
//    }
//
//
//    @GetMapping("/cartList/{memberId}")
////    경로상에 있는 숫자를 전달해주는 어노테이션: @PathVariable -> 문자가 있으면 error
//    public String cartList(@PathVariable Long memberId,
//                           Model model) {
//        System.out.println("memberId = " + memberId);
//        List<CartProductDTO> cartDTOList = cartService.findAll(memberId);
//        model.addAttribute("cartList", cartDTOList);
//        System.out.println("cartDTOList = " + cartDTOList);
//        return "cartPages/cartList";
//    }

}
















