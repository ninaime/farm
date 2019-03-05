package com.mime.farm.entity;
/**
 * 仓库表和商城表
 */
public class Store {
	private Integer id;//编号
	private Integer type;//类型1.仓库  2.商城
	private Integer seedId;//种子编号
	private Integer userId;//用户ID  外键
	private Integer inventory;//库存
	
}
