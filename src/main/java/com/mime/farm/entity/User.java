package com.mime.farm.entity;

import java.sql.Timestamp;

public class User {
	private Integer id;//用户编号
	private String nikename;//显示的用户名
	private String username;//用户名
	private String password;//密码
	private Integer sex;//性别
	private String avatar;//头像
	private Integer roleId;//用户状态 0正常用户  1管理员用户  2封号用户
	private Integer userLock;//锁 0不在线 1在线
	private Integer exp;//经验
	private Integer gold;//金币
	private Integer isDisable;//是否禁用
	private String ip;//登录IP
	private Timestamp gmtCreate;//创建时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNikename() {
		return nikename;
	}
	public void setNikename(String nikename) {
		this.nikename = nikename;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getUserLock() {
		return userLock;
	}
	public void setUserLock(Integer userLock) {
		this.userLock = userLock;
	}
	public Integer getExp() {
		return exp;
	}
	public void setExp(Integer exp) {
		this.exp = exp;
	}
	public Integer getGold() {
		return gold;
	}
	public void setGold(Integer gold) {
		this.gold = gold;
	}
	public Integer getIsDisable() {
		return isDisable;
	}
	public void setIsDisable(Integer isDisable) {
		this.isDisable = isDisable;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Timestamp getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Timestamp gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	
	
}
