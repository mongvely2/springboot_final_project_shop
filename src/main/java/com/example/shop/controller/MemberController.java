package com.example.shop.controller;

import com.example.shop.dto.MemberDTO;
import com.example.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/save")
    public String saveForm() {
        return "/memberPages/memberSave";
    }

    @PostMapping("/dup-check")
    public ResponseEntity duplicateCheck(@RequestParam("inputEmail") String inputEmail) {
        String checkResult = memberService.duplicateCheck(inputEmail);

        if (checkResult == null) {
            return new ResponseEntity<>("이미 사용중인 이메일 입니다!", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>("사용 가능한 이메일 입니다!", HttpStatus.OK);
        }
    }

    @PostMapping("/save")
    public String save(@Validated @ModelAttribute MemberDTO memberDTO, BindingResult bindingResult) {
        Long savedId = memberService.save(memberDTO);
        if (savedId > 0) {
            return "index";
//            return "memberPages/memberLogin";
        } else {
            return "memberPages/memberSave";
        }
    }
}
