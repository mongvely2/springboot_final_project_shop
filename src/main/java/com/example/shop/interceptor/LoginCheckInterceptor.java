package com.example.shop.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//  HandlerInterceptor 경우 인터페이스이기 때문에 implements 키워드를,
//  HandlerInterceptorAdapter는 추상 클래스이기 때문에 extends를 키워드를 사용
public class LoginCheckInterceptor implements HandlerInterceptor {
//    상속받는 HandlerIntercepter 사용중인 메서드 3가지: preHandle(), postHandle(), afterCompletion()

    //  @Override 메서드 재정의
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {
//        요청한 주소값을 가져오는 어노테이션 = request
        String requestURL = request.getRequestURI();
        System.out.println("requestURL_interceptor class = " + requestURL);
//        메서드 재정의(override)시 리턴타입, 메서드명, 매개변수는 건드릴 수 없기에 session을 따로 선언해줘야함
        HttpSession session = request.getSession();
        if (session.getAttribute("loginEmail") == null) {
//            로그인이 끝나면 다시 돌아갈 주소: redirectURL+requestURL
            response.sendRedirect("/member/login?redirectURL=" + requestURL);
            return false;
        }
//        로그인이 되어있으면 true -> 하던 요청대로 진행
        return true;
    }
}
