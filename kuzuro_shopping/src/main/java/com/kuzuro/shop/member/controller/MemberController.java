package com.kuzuro.shop.member.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kuzuro.shop.member.domain.MemberVO;
import com.kuzuro.shop.member.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	MemberService service;
	
	@Autowired
	BCryptPasswordEncoder passEncoder;
	
	// 회원가입 get
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public void getSignup() throws Exception {
		logger.info("getSignup");
	}
	
	// 회원가이 post
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String postSignup(MemberVO vo) throws Exception {
		logger.info("postSignup");
		
		// 패스워드 암호화
		String inputPass = vo.getUserPass();
		String pass = passEncoder.encode(inputPass);
		vo.setUserPass(pass);
		
		service.signup(vo);
		
		return "redirect:/";
	}
	
	// 로그인 get
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public void getSignin() throws Exception {
		logger.info("getSignin");
	}
	
	// 로그인 post
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String postSignin(MemberVO vo, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		logger.info("postSignin");
		
		MemberVO login = service.signin(vo);		// 회원 정보 조회
		HttpSession session = request.getSession();	// 세션 정보
		
		boolean passMatch = passEncoder.matches(vo.getUserPass(), login.getUserPass());	// 입력 비번과 저장된 비번 비교 // 입력한 비번과 데이터 베이스에 저장된 비번을 비교해서 동일하면 true, 그렇지않다면 false를 반환.
		
		if(login != null && passMatch) {			// 회원정보가 있고 입력된 정보와 일치하면
			session.setAttribute("member", login);	// 세션정보에 회원정보 저장
		} else {
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg", false);
			return "redirect:/member/signin";
		}
		
		return "redirect:/";
	}
	
	// 로그아웃
	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public String getSignout(HttpSession session) throws Exception {
		logger.info("getSignout");
		
		service.signout(session);
		
		return "redirect:/";
	}
}
