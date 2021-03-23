package com.kuzuro.shop.admin.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kuzuro.shop.admin.domain.CategoryVO;

@Repository
public class AdminDAOImpl implements AdminDAO {

	@Inject
	SqlSession sql;
	
	// 매퍼
	private static String namespace = "com.kuzuro.shop.mappers.adminMapper";
	@Override
	public List<CategoryVO> category() throws Exception {
		return sql.selectList(namespace + ".category");
	}

}
