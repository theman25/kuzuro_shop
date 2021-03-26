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
	
}
