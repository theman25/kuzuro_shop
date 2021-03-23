package com.kuzuro.shop.member.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class GoodsVO {
	/*
	create table tbl_goods (
		gdsNum		INT				not null,
		gdsName		VARCHAR(50)		not null,
		cateCode		VARCHAR(30)		not null,
		gdsPrice		INT				not null,
		gdsStock		INT				null,
		gdsDes		VARCHAR(500)	null,
		gdsImg		VARCHAR(200)	null,
		gdsDate		TIMESTAMP		default NOW(),
		primary key(gdsNum)  
	);
	*/
	
	private int gdsNum;
	private String gdsName;
	private String cateCode;
	private int gdsPrice;
	private int gdsStock;
	private String gdsDes;
	private String gdsImg;
	private Date gdsDate;
	
}
