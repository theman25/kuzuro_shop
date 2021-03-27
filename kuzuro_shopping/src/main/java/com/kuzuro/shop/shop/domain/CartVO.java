package com.kuzuro.shop.shop.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class CartVO {
	/*
	CREATE TABLE tbl_cart (
		cartNum		INT				NOT NULL,
		userId		VARCHAR(50)		NOT NULL,
		gdsNum		INT				NOT NULL,
		cartStock	INT				NOT NULL,
		addDate		TIMESTAMP		DEFAULT NOW(),
		PRIMARY KEY(cartNum, userId) 
	);
	*/
	private int cartNum;
	private String userId;
	private int gdsNum;
	private String cartStock;
	private Date addDate;
}
