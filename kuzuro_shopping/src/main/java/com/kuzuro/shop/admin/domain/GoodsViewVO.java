package com.kuzuro.shop.admin.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class GoodsViewVO extends GoodsVO{
	
	private String cateName;
	private String cateCodeRef;
	
}
