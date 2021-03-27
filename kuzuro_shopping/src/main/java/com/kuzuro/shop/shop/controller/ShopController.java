package com.kuzuro.shop.shop.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kuzuro.shop.admin.domain.GoodsViewVO;
import com.kuzuro.shop.member.domain.MemberVO;
import com.kuzuro.shop.shop.domain.CartListVO;
import com.kuzuro.shop.shop.domain.CartVO;
import com.kuzuro.shop.shop.domain.ReplyListVO;
import com.kuzuro.shop.shop.domain.ReplyVO;
import com.kuzuro.shop.shop.service.ShopService;

@Controller
@RequestMapping("/shop/*")
public class ShopController {

	private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
	
	@Inject
	private ShopService service;
	
	// 카테고리별 상품 리스트
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void getList(@RequestParam("c") int cateCode, @RequestParam("l") int level, Model model) throws Exception {
		logger.info("getList");
		
		List<GoodsViewVO> list = null;
		list = service.list(cateCode, level);
		
		model.addAttribute("list", list);
	}
	
	// 상품 조회
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public void getview(@RequestParam("n") int gdsNum, Model model) throws Exception {
		logger.info("getview");
		
		GoodsViewVO view = null;
		view = service.goodsView(gdsNum);
		model.addAttribute("view", view);
		
		// 상품소감목록 조회 ajax로 대체
		//List<ReplyListVO> replyList = null;
		//replyList = service.replyList(gdsNum);
		//model.addAttribute("replyList", replyList);
	}
	
	// 상품 조회 - 소감(댓글) 작성
	/*
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public String registReply(ReplyVO reply, HttpSession session) throws Exception {
		logger.info("registReply");
		
		MemberVO member = (MemberVO) session.getAttribute("member");
		reply.setUserId(member.getUserId());
		
		service.registReply(reply);
		
		return "redirect:/shop/view?n=" + reply.getGdsNum();
	}
	*/
	
	// 상품  소감(댓글) 작성 - ajax
	@ResponseBody
	@RequestMapping(value = "/view/registReply", method = RequestMethod.POST)
	public void registReplyAjax(ReplyVO reply, HttpSession session) throws Exception {
		logger.info("registReplyAjax");
		
		MemberVO member = (MemberVO) session.getAttribute("member");
		reply.setUserId(member.getUserId());
		
		service.registReply(reply);
	}
	
	// 상품 소감(댓글) 목록 - ajax
	@ResponseBody
	@RequestMapping(value = "/view/replyList", method = RequestMethod.GET)
	public List<ReplyListVO> getReplyList(@RequestParam("n") int gdsNum) throws Exception {
		logger.info("getReplyList");
		
		List<ReplyListVO> replyList = null;
		replyList = service.replyList(gdsNum);
		
		return replyList;
	}
	
	// 상품 소감(댓글) 삭제 - ajax
	@ResponseBody
	@RequestMapping(value = "/view/deleteReply", method = RequestMethod.POST)
	public int deleteReply(ReplyVO reply, HttpSession session) throws Exception {
		logger.info("deleteReply");
		
		int result = 0;
		
		MemberVO member = (MemberVO) session.getAttribute("member");
		String userId = service.idCheck(reply.getRepNum());
		
		if(member.getUserId().equals(userId)) {
			reply.setUserId(member.getUserId());
			service.deleteReply(reply);
			result = 1;
		}
		
		return result;
	}
	
	// 상품 소감(댓글) 수정 - ajax
	@ResponseBody
	@RequestMapping(value = "/view/modifyReply", method = RequestMethod.POST)
	public int modifyReply(ReplyVO reply, HttpSession session) throws Exception {
		logger.info("modifyReply");
		
		int result = 0;
		
		MemberVO member = (MemberVO) session.getAttribute("member");
		String userId = service.idCheck(reply.getRepNum());
		
		if(member.getUserId().equals(userId)) {
			reply.setUserId(member.getUserId());
			service.modifyReply(reply);
			result = 1;
		}
		
		return result;
	}
	
	// 카트 담기
	@ResponseBody
	@RequestMapping(value = "/view/addCart", method = RequestMethod.POST)
	public int addCart(CartVO cart, HttpSession session) throws Exception {
		logger.info("addCart");
		
		int result = 0;
		
		MemberVO member = (MemberVO) session.getAttribute("member");
		if(member != null) {
			cart.setUserId(member.getUserId());
			service.addCart(cart);
			result = 1;
		}
		
		return result;
	}
	
	// 카트 리스트
	@RequestMapping(value = "/cartList", method = RequestMethod.GET)
	public void getCartList(HttpSession session, Model model) throws Exception {
		logger.info("getCartList");
		
		MemberVO member = (MemberVO) session.getAttribute("member");
		String userId = member.getUserId();
		
		List<CartListVO> cartList = null;
		cartList = service.cartList(userId);
		model.addAttribute("cartList", cartList);
	}
	
	// 카트 삭제
	@ResponseBody
	@RequestMapping(value = "/deleteCart", method = RequestMethod.POST)
	public int deleteCart(HttpSession session, @RequestParam(value = "chbox[]") List<String> chArr, CartVO cart) throws Exception {
		logger.info("deleteCart");
		
		int result = 0;
		int cartNum = 0;
		
		MemberVO member = (MemberVO) session.getAttribute("member");
		String userId = member.getUserId();
		
		if(member != null) {
			cart.setUserId(userId);
			
			for(String i : chArr) {
				cartNum = Integer.parseInt(i);
				cart.setCartNum(cartNum);
				service.deleteCart(cart);
			}
			result = 1;
		}
		return result;
	}
}
