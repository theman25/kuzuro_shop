package com.kuzuro.shop.shop.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kuzuro.shop.admin.domain.GoodsViewVO;
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
}
