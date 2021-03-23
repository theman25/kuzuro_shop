package com.kuzuro.shop.admin.service;

import java.util.List;

import com.kuzuro.shop.admin.domain.CategoryVO;

public interface AdminService {

	// 카테고리
	public List<CategoryVO> category() throws Exception;
}
