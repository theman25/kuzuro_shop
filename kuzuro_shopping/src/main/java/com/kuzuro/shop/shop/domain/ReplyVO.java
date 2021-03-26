package com.kuzuro.shop.shop.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ReplyVO {
	/*
	CREATE TABLE tbl_reply (
		gdsNum	INT				NOT NULL,		-- 상품번호
		userId	VARCHAR(50)		NOT NULL,		-- 사용자 아이디
		repNum	INT				NOT NULL ,		-- 댓글번호
		repCon	VARCHAR(2000)	NOT NULL,		-- 댓글내용
		repDate	TIMESTAMP		DEFAULT NOW(),	-- 댓글작성일자
		PRIMARY KEY(gdsNum, repNum) 
	);
	*/
	private int gdsNum;
	private String userId;
	private int repNum;
	private String repCon;
	private Date repDate;
}
