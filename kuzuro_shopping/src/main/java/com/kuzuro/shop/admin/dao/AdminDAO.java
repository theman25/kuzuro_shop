package com.kuzuro.shop.admin.dao;

import java.util.List;

import com.kuzuro.shop.admin.domain.CategoryVO;

public interface AdminDAO {

	// 카테고리
	public List<CategoryVO> category() throws Exception;

}
