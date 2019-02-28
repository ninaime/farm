package com.mime.farm.dao;

import java.util.List;

import com.mime.farm.pojo.User;

/**
 * Dao层接口
 */
public interface SysUserDao {
	//public boolean register(String username,String password);
	
	public List<User> findAll();
}
