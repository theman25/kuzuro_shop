package com.kuzuro.shop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kuzuro.shop.member.domain.MemberVO;

public class AdminInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object obj) throws Exception {
		
		HttpSession session = req.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		
		//if(member == null || member.getVerify() != 9) {
		//	res.sendRedirect("/");
		//	return false;
		//}
		
		// 로그인 하지 않은 경우 로그인 화면으로 
		if(member == null) {
			res.sendRedirect("/member/signin");
			return false;
		}
		// 관리자가 아닌 경우 일반화면으로
		if(member.getVerify() != 9) {
			res.sendRedirect("/");
			return false;
		}
		return true;
	}
}
