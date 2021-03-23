package com.kuzuro.shop.member.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MemberVO {
	/*
	create table tbl_member (
		userId		VARCHAR(50)		not null,
		userPass	VARCHAR(100)	not null,
		userName	VARCHAR(30)		not null,
		userPhon	VARCHAR(20)		not null,
		userAddr1	VARCHAR(20)		null,
		userAddr2	VARCHAR(50)		null,
		userAddr3	VARCHAR(50)		null,
		regiDate	TIMESTAMP 		default NOW(),
		verify		INT				default 0,
		primary key(userId)
	);
	*/
	
	private String userId;
	private String userPass;
	private String userName;
	private String userPhon;
	private String userAddr1;
	private String userAddr2;
	private String userAddr3;
	private Date regDate;
	private int verify;
	
}
