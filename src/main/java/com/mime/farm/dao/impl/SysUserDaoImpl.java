package com.mime.farm.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;

import com.mime.farm.dao.SysUserDao;
import com.mime.farm.util.DBUtils;

/**
 * Dao层实现类
 */
public class SysUserDaoImpl implements SysUserDao {
	protected SysUserDaoImpl() {}

	/**
	 * register 注册用户
	 * @param conn 事物操作
	 */
	@Override
	public boolean register(String username, String password,Connection conn) {
		String sql = "insert into sys_user(username,password) values(?,?)";
		ArrayList<Object> parameter = new ArrayList<Object>();
		parameter.add(password);
		int result = DBUtils.DML(sql, parameter,conn);
		if (result > 0)
			return true;
		else
			return false;
	}

}
