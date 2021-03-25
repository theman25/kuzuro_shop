package com.kuzuro.shop.admin.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		String imgUploadPath = uploadPath + File.separator + "imgUpload";	// 이미지를 업로드할 폴더를 설정 = /uploadPath/imgUpload
		String ymdPath = UploadFileUtils.calcPath(imgUploadPath);			// 위 폴더를 기준으로 연월일 폴더 생성
		String fileName = null;												// 기본 경로와 별개로 작성되는 경로 + 파일이름
		
		//if(file != null) {	// 파일을 추가 하지 않더라도 파일은 값과 용량을 가지고 있음
		// input 박스에 첨부된 파일이 있다면(=첨부된 파일의 이름이 있다면)
		if(file.getOriginalFilename() != null && file.getOriginalFilename() != "") {	// 유일하게 값이 없는 파일 이름		
			fileName = UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath);
			vo.setGdsImg(File.separator + "imgUpload" + ymdPath + File.separator + fileName);										// gdsImg에 원본 파일 경로 + 파일명 저장
			vo.setGdsThumbImg(File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);	// gdsThumbImg에 썸네일 파일 경로 + 썸네일 파일명 저장 
		} 
		// input 박스에 첨부된 파일이 없다면(=첨부된 파일의 이름이 없다면)
		else {
			// 미리 준비된 none.png파일을 대신 출력
			fileName = File.separator + "images" + File.separator + "none.png";
			vo.setGdsImg(fileName);			// gdsImg에 원본 파일 경로 + 파일명 저장
			vo.setGdsThumbImg(fileName);	// gdsThumbImg에 썸네일 파일 경로 + 썸네일 파일명 저장
		}
		
		adminService.register(vo);
		
		return "redirect:/admin/index";
	}

	// 상품목록 get
	@RequestMapping(value = "/goods/list", method = RequestMethod.GET)
	public void getGoodsList(Model model) throws Exception {
		logger.info("getGoodsList");
		
		//List<GoodsVO> goodsList = null;
		List<GoodsViewVO> goodsList = null;
		goodsList = adminService.goodsList();
		
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
	public String postGoodsModify(GoodsVO vo, MultipartFile file, HttpServletRequest requset) throws Exception {
		logger.info("postGoodsModify");
		
		// 새로운 파일이 등록 되었으면
		if(file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
			// 기존 파일 삭제
			new File(uploadPath + requset.getParameter("gdsImg")).delete();
			new File(uploadPath + requset.getParameter("gdsThumbImg")).delete();
			// 새로운 파일 등록
			String imgUploadPath = uploadPath + File.separator + "imgUpload";
			String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
			String fileName = UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath);

			vo.setGdsImg(File.separator + "imgUpload" + ymdPath + File.separator + fileName);
			vo.setGdsThumbImg(File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
		}
		// 새로운 파일이 등록 되지 않았으면
		else {
			// 기존 이미지를 그대로 사용
			vo.setGdsImg(requset.getParameter("gdsImg"));
			vo.setGdsThumbImg(requset.getParameter("gdsThumbImg"));
		}
		
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

	//ck 에이터에서 파일 업로드
	@RequestMapping(value = "/goods/ckUpload", method = RequestMethod.POST)
	public void postCKEditorImgUpload(HttpServletRequest request, HttpServletResponse response, 
									@RequestParam MultipartFile upload) throws Exception {
		logger.info("postCKEditorImgUpload");
		
		// 랜덤 문자 생성
		UUID uid = UUID.randomUUID();
		
		OutputStream out = null;
		PrintWriter printWriter = null;
		
		// 인코딩
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		try {
			// 파일 이름 가져오기
			String fileName = upload.getOriginalFilename();
			byte[] bytes = upload.getBytes();
			
			// 업로드 경로
			String ckUploadPath = uploadPath + File.separator + "ckUpload" + File.separator + uid + "_" + fileName;
			
			out = new FileOutputStream(new File(ckUploadPath));
			out.write(bytes);
			out.flush();	// out에 저장된 데이터를 전송하고 초기화
			
			String callback = request.getParameter("CKEditorFuncNum");
			printWriter = response.getWriter();
			String fileUrl = "/ckUpload/" + uid + "_" + fileName;	// 작성화면
			
			// 업로드 시 메세지 출력
			// ck 에디터가 업데이트 되면서 아래 방밥으로 변경
			printWriter.println("{\"filename\" : \"" + fileName+"\", \"uploaded\" : 1, \"url\":\""+fileUrl+"\"}");
			//printWriter.println("{'filename' : '" + fileName + "', 'uploaded' : 1, 'url':'" + fileUrl + "'}");
			printWriter.flush();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if(out != null) { out.close(); }
				if(printWriter != null) { printWriter.close(); }
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
