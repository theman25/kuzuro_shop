package com.kuzuro.shop.admin.service;

import java.util.List;

import com.kuzuro.shop.admin.domain.CategoryVO;
import com.kuzuro.shop.admin.domain.GoodsVO;

public interface AdminService {

	// 카테고리
	public List<CategoryVO> category() throws Exception;
	
	// 상품등록
	public void register(GoodsVO vo) throws Exception;

	// 상품목록
	public List<GoodsVO> goodsList() throws Exception;
	
	// 상품상세
	public GoodsVO goodsView(int gdsNum) throws Exception;
	
}
