package com.kuzuro.shop.admin.dao;

import java.util.List;

import com.kuzuro.shop.admin.domain.CategoryVO;
import com.kuzuro.shop.admin.domain.GoodsVO;

public interface AdminDAO {

	// 카테고리
	public List<CategoryVO> category() throws Exception;
	
	// 상품등록
	public void register(GoodsVO vo) throws Exception;

}
