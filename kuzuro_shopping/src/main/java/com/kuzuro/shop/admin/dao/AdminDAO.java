package com.kuzuro.shop.admin.dao;

import java.util.List;

import com.kuzuro.shop.admin.domain.CategoryVO;
import com.kuzuro.shop.admin.domain.GoodsVO;
import com.kuzuro.shop.admin.domain.GoodsViewVO;

public interface AdminDAO {

	// 카테고리
	public List<CategoryVO> category() throws Exception;
	
	// 상품등록
	public void register(GoodsVO vo) throws Exception;

	// 상품목록
	public List<GoodsVO> goodsList() throws Exception;
	
	// 상품상세 + 카테고리
	public GoodsViewVO goodsView(int gdsNum) throws Exception;
	
	// 상품수정
	public void goodsModify(GoodsVO vo) throws Exception;
	
	// 상품삭제
	public void goodsDelete(int gdsNum) throws Exception;
}
