package com.kuzuro.shop.admin.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class CategoryVO {
	/*
	create table tbl_goods_category (
		cateName		VARCHAR(20)		not null,
		cateCode		VARCHAR(30)		not null,
		cateCodeRef	VARCHAR(30)		null,
		primary key(cateCode),
		foreign key(cateCodeRef) references tbl_goods_category(cateCode)
	);
	*/
	
	private String cateName;
	private String cateCode;
	private String cateCodeRef;
	private int level;
}
