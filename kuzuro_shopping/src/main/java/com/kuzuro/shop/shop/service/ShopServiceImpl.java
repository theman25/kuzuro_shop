package com.kuzuro.shop.shop.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kuzuro.shop.admin.domain.GoodsViewVO;
import com.kuzuro.shop.shop.dao.ShopDAO;
import com.kuzuro.shop.shop.domain.ReplyListVO;
import com.kuzuro.shop.shop.domain.ReplyVO;

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

	// 상품 조회
	@Override
	public GoodsViewVO goodsView(int gdsNum) throws Exception {
		return dao.goodsView(gdsNum);
	}

	// 상품 소감(댓글) 작성
	@Override
	public void registReply(ReplyVO reply) throws Exception {
		dao.registReply(reply);
	}

	// 상품 소감(댓글) 리스트 조회
	@Override
	public List<ReplyListVO> replyList(int gdsNum) throws Exception {
		return dao.replyList(gdsNum);
	}

	// 상품 소감(댓글) 삭제
	@Override
	public void deleteReply(ReplyVO reply) throws Exception {
		dao.deleteReply(reply);
	}

	// 아이디 체크
	@Override
	public String idCheck(int repNum) throws Exception {
		return dao.idCheck(repNum);
	}

	// 상품 소감(댓글) 수정
	@Override
	public void modifyReply(ReplyVO reply) throws Exception {
		dao.modifyReply(reply);
	}
	
}
