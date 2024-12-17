package com.chldbwls.sns.interceptor;

import java.io.IOException;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PermissionInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(
			HttpServletRequest request
			, HttpServletResponse response
			, Object handler) throws IOException {
		
		HttpSession session = request.getSession();
		
		// null이 가능한 mapper class integer
		// null이냐 아니냐를 구분하기 위해
		// null = 로그인이 안 된 상태
		Integer userId = (Integer)session.getAttribute("userId");
		
		// /post/list-view
		// /post로 시작되는 건 로그인 된 후에 가능하다는 패턴 확인
		String uri = request.getRequestURI();
		
		// 로그인이 안 된 상태에서 메모와 관련된 기능 페이지의 접근을 막는다
		// 로그인 페이지로 이동
		// null이냐 아니냐로 로그인 됐다 안 됐다 확인 가능
		if(userId == null) {
			// 로그인이 안 되어있다면
			// /post로 시작하는 url path 확인
			if(uri.startsWith("/post")) {
				
				// 로그인 페이지로 리다이렉트 정보를 response에 담는다.
				response.sendRedirect("/user/login-view");
				return false; // controller로의 진행 막음(true일 경우만 진행해서)
			}
			
		} else {
			// 예외처리
			// 로그인이 되어있는 경우 사용자와 관련된 페이지 접근을 막는다
			// 메모 리스트 페이지로 이동
			// /user로 시작하는 url path 확인
			if(uri.startsWith("/user")) {
				response.sendRedirect("/home/main-view");
				return false; // 원래 하려던 기능 중단
			}
			
		}
		
		
		
		
		// 다 지나서 내려온다면 true로 판단하여 true로 return
		return true;
	}

}
