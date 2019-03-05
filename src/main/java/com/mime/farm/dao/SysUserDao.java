package com.mime.farm.dao;

import java.sql.Connection;

/**
 * Dao层接口
 */
public interface SysUserDao {
	boolean register(String username,String password,Connection conn);
	
}
