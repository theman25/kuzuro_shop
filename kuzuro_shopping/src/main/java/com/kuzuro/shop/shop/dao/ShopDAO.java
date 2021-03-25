package com.kuzuro.shop.shop.dao;

import java.util.List;

import com.kuzuro.shop.admin.domain.GoodsViewVO;

public interface ShopDAO {

	// 카테고리별 상품 리스트 : 1차분류
	public List<GoodsViewVO> list(int cateCode, int cateCodeRef) throws Exception;
	
	// 카테고리별 상품 리스트 : 2차분류
	public List<GoodsViewVO> list(int cateCode) throws Exception;

	// 상품 조회
	public GoodsViewVO goodsView(int gdsNum) throws Exception;
}
