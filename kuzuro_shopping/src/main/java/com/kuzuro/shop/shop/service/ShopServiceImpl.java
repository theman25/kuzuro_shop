package com.kuzuro.shop.shop.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kuzuro.shop.admin.domain.GoodsViewVO;
import com.kuzuro.shop.shop.dao.ShopDAO;

@Service
public class ShopServiceImpl implements ShopService {

	@Inject
	private ShopDAO dao;

	// 카테고리별 상품 리스트
	@Override
	public List<GoodsViewVO> list(int cateCode, int level) throws Exception {
		
		int cateCodeRef = 0;
		
		if(level == 1) {	// 1차 분류
			cateCodeRef = cateCode;
			return dao.list(cateCode, cateCodeRef);
		}
		else {				// 2차 분류
			return dao.list(cateCode);
		}
	}
	
}
