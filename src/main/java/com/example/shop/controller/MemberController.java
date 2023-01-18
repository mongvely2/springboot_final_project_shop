package com.example.shop.controller;

import com.example.shop.dto.MemberDTO;
import com.example.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/save")
    public String saveForm() {
        return "/memberPages/memberSave";
    }

    @PostMapping("/dupCheck")
    public ResponseEntity duplicateCheck(@RequestParam("inputEmail") String inputEmail) {
        String checkResult = memberService.duplicateCheck(inputEmail);

        if (checkResult == null) {
            return new ResponseEntity<>("이미 사용중인 이메일 입니다!", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>("사용 가능한 이메일 입니다!", HttpStatus.OK);
        }
    }

    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        Long savedId = memberService.save(memberDTO);
        if (savedId > 0) {
            return "memberPages/memberLogin";
        } else {
            return "memberPages/memberSave";
        }
    }

    @GetMapping("/login")
    public String login() {
        return "memberPages/memberLogin";
    }

    @PostMapping("/login")
    public @ResponseBody String login(@ModelAttribute MemberDTO memberDTO,
                                      HttpSession session,
                                      @RequestParam(value = "redirectURL", defaultValue = "/") String redirectURL) throws Exception {
        MemberDTO memberLogin = memberService.login(memberDTO);
        if (memberLogin != null) {
        session.setAttribute("loginSession", memberLogin);
        session.setAttribute("loginEmail", memberLogin.getMemberEmail());
//        인터셉터에 걸려 로그인한 사용자가 직전에 요청한 페이지로 보내주기 위한 redirect:/직전요청주소
//        인터셉터에 걸리지 않고 로그인을 한 사용자는 defaultValue에 의해 index 호출
            return redirectURL;     //"redirect:"+redirectURL;
        } else {
            return null;
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    @GetMapping("/adminPage")
    public String adminPage() {
        return "/memberPages/adminPage";
    }

    @GetMapping("/memberList")
    public String memberListPaging(@PageableDefault(page = 1) Pageable pageable,
                                   Model model) {
        Page<MemberDTO> memberDTOList = memberService.memberListPaging(pageable);
        model.addAttribute("memberList", memberDTOList);
//        start 페이지 end 페이지 계산방식 -> 삼항연산자 사용
//        int nowPage = boardDTOList.getNumber();
        int blockLimit = 5;
        int startPage = (((int)(Math.ceil((double) pageable.getPageNumber() / blockLimit))) -1) * blockLimit+1;
        int endPage = ((startPage+blockLimit-1)<memberDTOList.getTotalPages()) ? startPage+blockLimit-1 : memberDTOList.getTotalPages();
//        삼항연산자 = if/else 를 간단하게 작성한 것, 아래는 풀이를 위한 코드임(기능구현 상관x)
        int test = 10;
        int num = (test > 5) ? test : 100;    // 아래 if문이랑 똑같음
        if (test > 5) {
            num = test;
        } else {
            num = 100;
        }
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "memberPages/memberListPaging";
    }
}





















