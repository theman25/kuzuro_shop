package com.kuzuro.shop.admin.controller;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kuzuro.shop.admin.domain.CategoryVO;
import com.kuzuro.shop.admin.domain.GoodsVO;
import com.kuzuro.shop.admin.domain.GoodsViewVO;
import com.kuzuro.shop.admin.service.AdminService;
import com.kuzuro.shop.utils.UploadFileUtils;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/admin/*")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Inject
	AdminService adminService;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	// 관리자 화면
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public void getIndex() throws Exception {
		logger.info("getIndex");
	}
	
	// 상품등록 get
	@RequestMapping(value = "/goods/register", method = RequestMethod.GET)
	public void getGoodsRegister(Model model) throws Exception {
		logger.info("getGoodsRegister");
		
		List<CategoryVO> category = null;
		category = adminService.category();
		model.addAttribute("category", JSONArray.fromObject(category));
	}
	
	// 상품등록 post
	//@RequestMapping(value = "/goods/register", method = RequestMethod.POST)
	//public String postGoodsRegister(GoodsVO vo) throws Exception {
	//	logger.info("postGoodsRegister");
	//	adminService.register(vo);
	//	return "redirect:/admin/index";
	//}
	
	// 상품등록 + 이미지 post
	@RequestMapping(value = "/goods/register", method = RequestMethod.POST)
	public String postGoodsRegister(GoodsVO vo, MultipartFile file) throws Exception {
		logger.info("postGoodsRegister");
		
		String imgUploadPath = uploadPath + File.separator + "imgUpload";
		String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
		String fileName = null;
		
		if(file != null) {
			fileName = UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath); 
		} else {
			fileName = uploadPath + File.separator + "images" + File.separator + "none.png";
		}
		
		vo.setGdsImg(File.separator + "imgUpload" + ymdPath + File.separator + fileName);
		vo.setGdsThumbImg(File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
		
		adminService.register(vo);
		
		return "redirect:/admin/index";
	}
		
	// 상품목록 get
	@RequestMapping(value = "/goods/list", method = RequestMethod.GET)
	public void getGoodsList(Model model) throws Exception {
		logger.info("getGoodsList");
		
		List<GoodsVO> goodsList = adminService.goodsList();
		
		model.addAttribute("goodsList", goodsList);
	}
	
	// 상품조회 get
	@RequestMapping(value = "/goods/view", method = RequestMethod.GET)
	public void getGoodsView(@RequestParam("n") int gdsNum, Model model) throws Exception {
		logger.info("getGoodsView");
		
		//GoodsVO goods = adminService.goodsView(gdsNum);
		GoodsViewVO goods = adminService.goodsView(gdsNum);
		
		model.addAttribute("goods", goods);
	}
	
	// 상품수정 get
	@RequestMapping(value = "/goods/modify", method = RequestMethod.GET)
	public void getGoodsModify(@RequestParam("n") int gdsNum, Model model) throws Exception {
		logger.info("getGoodsModify");
		
		List<CategoryVO> category = null;
		category = adminService.category();
		
		//GoodsVO goods = null;
		GoodsViewVO goods = null;
		goods = adminService.goodsView(gdsNum);
		
		model.addAttribute("category", JSONArray.fromObject(category));
		model.addAttribute("goods", goods);
	}
	
	// 상품수정 post
	@RequestMapping(value = "/goods/modify", method = RequestMethod.POST)
	public String postGoodsModify(GoodsVO vo) throws Exception {
		logger.info("postGoodsModify");
		//logger.info("vo : " + vo);
		
		adminService.goodsModify(vo);
		
		return "redirect:/admin/index";
	}
	
	// 상품삭제 post
	@RequestMapping(value = "/goods/delete", method = RequestMethod.POST)
	public String postGoodsDelete(@RequestParam("n") int gdsNum) throws Exception {
		logger.info("postGoodsDelete");
		
		adminService.goodsDelete(gdsNum);
		
		return "redirect:/admin/index";
	}

}
