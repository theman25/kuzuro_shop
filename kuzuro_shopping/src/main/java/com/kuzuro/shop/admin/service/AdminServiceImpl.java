package com.kuzuro.shop.admin.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kuzuro.shop.admin.dao.AdminDAO;
import com.kuzuro.shop.admin.domain.CategoryVO;

@Service
public class AdminServiceImpl implements AdminService {

	@Inject
	private AdminDAO dao;
	
	@Override
	public List<CategoryVO> category() throws Exception {
		return dao.category();
	}

}
