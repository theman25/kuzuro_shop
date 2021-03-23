package com.kuzuro.shop.member.dao;

import com.kuzuro.shop.member.domain.MemberVO;

public interface MemberDAO {

	// 회원가입
	public void signup(MemberVO vo) throws Exception;
	
	// 로그인
	public MemberVO signin(MemberVO vo) throws Exception;

}
