package com.mime.farm.dao.impl;

import java.util.List;

import com.mime.farm.dao.SysUserDao;
import com.mime.farm.pojo.User;
import com.mime.farm.util.jdbc.DBUtils;
import com.mime.farm.util.jdbc.DefaultMapper;

/**
 * Dao层实现类
 */
public class SysUserDaoImpl implements SysUserDao{
	protected SysUserDaoImpl() {}
	/**
	 * register 注册用户
	 * @param conn 事物操作
	 */
	/*
	 * @Override public boolean register(String username,String password) { String
	 * sql = "insert into sys_user(username,password) values(?,?)"; // List<Object>
	 * parameter = new ArrayList<Object>(); // parameter.add(username); //
	 * parameter.add(password); String[] params = {username,password}; int result =
	 * DBUtils.queryResult(sql, params, new DefaultMapper(), FitType.class);
	 * if(result>0) return true; else return false; }
	 */
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<User> findAll(){
		String sql = "select * from sys_user";
//		List<Object> parameter = new ArrayList<Object>();
//		parameter.add(username);
//		parameter.add(password);
		return DBUtils.queryResult(sql, null, new DefaultMapper(), User.class);
	}
	
}
