package com.kuzuro.shop.shop.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kuzuro.shop.admin.domain.GoodsViewVO;
import com.kuzuro.shop.shop.domain.ReplyListVO;
import com.kuzuro.shop.shop.domain.ReplyVO;

@Repository
public class ShopDAOImpl implements ShopDAO {
	
	@Inject
	private SqlSession sql;
	
	// 매퍼
	private static String namespace = "com.kuzuro.shop.mappers.shopMapper";

	// 카테고리별 상품 리스트 : 1차 분류
	@Override
	public List<GoodsViewVO> list(int cateCode, int cateCodeRef) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cateCode", cateCode);
		map.put("cateCodeRef", cateCodeRef);
		
		return sql.selectList(namespace + ".list_1", map);
	}
	
	// 카테고리별 상품 리스트 : 2차 분류
	@Override
	public List<GoodsViewVO> list(int cateCode) throws Exception {
		return sql.selectList(namespace + ".list_2", cateCode);
	}

	// 상품 조회
	@Override
	public GoodsViewVO goodsView(int gdsNum) throws Exception {
		return sql.selectOne("com.kuzuro.shop.mappers.adminMapper.goodsView", gdsNum);
	}

	// 상품 소감(댓글) 작성
	@Override
	public void registReply(ReplyVO reply) throws Exception {
		sql.insert(namespace + ".registReply", reply);
	}

	// 상품 소감(댓글) 리스트 조회
	@Override
	public List<ReplyListVO> replyList(int gdsNum) throws Exception {
		return sql.selectList(namespace + ".replyList", gdsNum);
	}

	// 상품 소감(댓글) 삭제
	@Override
	public void deleteReply(ReplyVO reply) throws Exception {
		sql.delete(namespace + ".deleteReply", reply);
	}

	// 아이디 체크
	@Override
	public String idCheck(int repNum) throws Exception {
		return sql.selectOne(namespace + ".replyUserIdCheck", repNum);
	}
	
}
