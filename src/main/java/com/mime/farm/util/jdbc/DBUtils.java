package com.mime.farm.util.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.alibaba.fastjson.JSON;
import com.mime.farm.common.CustomException;



public class DBUtils {
	//数据库连接驱动
	public static String DRIVER = null;
	//数据库连接路径
	public static String URL=null;
	//数据库用户名
	public static String USERNAME=null;
	//数据库密码
	public static String PASSWORD=null;
	
	static {
		Properties properties = new Properties();
		// 使用ClassLoader加载properties配置文件生成对应的输入流
	    InputStream in = DBUtils.class.getClassLoader().getResourceAsStream("config/datasource.properties");
	    // 使用properties对象加载输入流
	    try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("config/datasource.properties文件找不到");
		}
	    //获取key对应的value值
	    URL = properties.getProperty("url");
	    DRIVER = properties.getProperty("driver");
	    USERNAME = properties.getProperty("username");
	    PASSWORD = properties.getProperty("password");
	}

	/**
	 * openConnection 开启数据库连接
	 * @exception 异常已在此处处理
	 * @return 没有返回值
	 */
	public static Connection getConnection() throws CustomException{
		try {
			//1.注册驱动
			Class.forName(DRIVER);
			//2.编写URL
			//3.打开connection连接
			Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			System.out.println("数据库连接加载成功");
			return conn;//连接成功后，返回conn连接给方法的调用者
		} catch (ClassNotFoundException e) {
			throw new CustomException("注册JDBC驱动异常！");
		}  catch (SQLException e) {
			e.printStackTrace();
			throw new CustomException("创建数据库connection连接失败！");
		}
	}
	
	
	
	/*
	 * 查询结果
     * @param sql        sql文
     * @param params    参数
     * @param handler    结果处理集
     * @return            返回结果集
     */
    public static<T> List<?> queryResult(String sql,String[] params,ResultMapper<T> handler,Class<T> c){
        List<Map<String,Object>> results = null;
        Connection conn = null;
        ResultSet rs  = null;
        PreparedStatement stmt = null;
        try {
        	conn = getConnection();
        	stmt = conn.prepareStatement(sql);
            for (int i = 0, n = (params!=null?params.length:0); i < n; i++) {
            	stmt.setString(i, params[i]);
            } 
            rs = stmt.executeQuery();
            if(rs!=null){
                results = new ArrayList<>();     
                String[] name = null;
                ResultSetMetaData rsm = null;
                while(rs.next()){
                    if(rsm==null){
                        rsm = rs.getMetaData();
                        System.out.println(JSON.toJSONString(rsm));
                        int count = rsm.getColumnCount();    
                        System.out.println(count);
                        name = new String[count];
                        for(int i = 0;i<count;i++){
                            name[i] = rsm.getColumnName(i+1);
                        }
                    }
                    Map<String, Object> map = new HashMap<>();
                    // 开始填充值
                    for(String keys:name){
                        map.put(keys, rs.getString(keys));
                    }
                    results.add(map);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeAll(rs, stmt, conn);
        }
        // 开始处理结合
        List<T> lists = new ArrayList<>();
        if(results!=null){
            for(Map<String, Object> e:results){
                T t = (T) handler.convert(e,c);
                lists.add(t);
            }
            return lists;
        }else{ 
            return null;
        }
    }
	
	
	
	/**
	 * DML DML操作集封装
	 * @exception pstmt = conn.prepareStatement(sql)会抛出空指针异常
	 * @param sql SQL语句
	 * @param conditionMap SQL语句中问号的代参
	 * @return 返回数据库受影响的行数
	 */
	public static int DML(String sql,List<Object> parameter){
		int result =0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			//获取数据库连接
			conn = DBUtils.getConnection();
			//开启事物
			conn.setAutoCommit(false);
			//获取数据库操作对象
			pstmt = conn.prepareStatement(sql);
			//判断传进来的参数是否为空，如果为空则不需要添加参数
	    	if(parameter!=null&&parameter.size()!=0){
	    		//遍历参数
	    		for(int i=0;i<parameter.size();i++){
	    			//接收参数的值
	    			Object paramValue = parameter.get(i);
	    			//判断值是否为Integer类型
	    			int j=i+1;
	    			if("java.lang.Integer".equalsIgnoreCase(paramValue.getClass().getName())){
	    				//如果是则将参数赋值
	    				pstmt.setInt(j, Integer.parseInt(paramValue.toString()));
	    			}else if("java.lang.String".equalsIgnoreCase(paramValue.getClass().getName())){
	    				pstmt.setString(j, paramValue.toString());
	    			}
	    			//此处应该再加一个date判断
	    		}
	    	}
			result = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("数据连接异常！");
		}  catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("数据操作异常！");
		} finally {
			DBUtils.closeAll(null, pstmt, conn);
		}
		return result;
	}
	
	/**
	 * DML 使用事物操作DML集
	 * @exception pstmt = conn.prepareStatement(sql)会抛出空指针异常
	 * @param sql SQL语句
	 * @param conditionMap SQL语句中问号的代参
	 * @return 返回数据库受影响的行数
	 */
	public static int DMLTransaction(String sql,List<Object> parameter,Connection conn){
		int result =0;
		PreparedStatement pstmt = null;
		try {
			//获取数据库连接
			conn = DBUtils.getConnection();
			//开启事物
			conn.setAutoCommit(false);
			//获取数据库操作对象
			pstmt = conn.prepareStatement(sql);
			//判断传进来的参数是否为空，如果为空则不需要添加参数
	    	if(parameter!=null&&parameter.size()!=0){
	    		//遍历参数
	    		for(int i=0;i<parameter.size();i++){
	    			//接收参数的值
	    			Object paramValue = parameter.get(i);
	    			//判断值是否为Integer类型
	    			int j=i+1;
	    			if("java.lang.Integer".equalsIgnoreCase(paramValue.getClass().getName())){
	    				//如果是则将参数赋值
	    				pstmt.setInt(j, Integer.parseInt(paramValue.toString()));
	    			}else if("java.lang.String".equalsIgnoreCase(paramValue.getClass().getName())){
	    				pstmt.setString(j, paramValue.toString());
	    			}
	    			//此处应该再加一个date判断
	    		}
	    	}
			result = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("数据连接异常！");
		}  catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("数据操作异常！");
		} finally {
			DBUtils.closeAll(null, pstmt, null);
		}
		return result;
	}
	
	
	/**
	 * @param sql SQL语句
	 * @param conditionMap SQL语句中问号的代参
	 * @return 返回查询多条的数据
	 */
	public static List<Map<String, Object>> query(String sql,List<Object> parameter){
		//定义数据库连接对象
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    //定义返回值容器
	    List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
	    try {
	    	//打开数据库连接
	    	conn = getConnection();
	    	//获取数据库操作对象
	    	pstmt = conn.prepareStatement(sql);
	    	//判断传进来的参数是否为空，如果为空则不需要添加参数
	    	if(parameter!=null&&parameter.size()!=0){
	    		//遍历参数
	    		for(int i=0;i<parameter.size();i++){
	    			//接收参数的值
	    			Object paramValue = parameter.get(i);
	    			//判断值是否为Integer类型
	    			int j=i+1;
	    			if("java.lang.Integer".equalsIgnoreCase(paramValue.getClass().getName())){
	    				//如果是则将参数赋值
	    				pstmt.setInt(j, Integer.parseInt(paramValue.toString()));
	    			}else if("java.lang.String".equalsIgnoreCase(paramValue.getClass().getName())){
	    				pstmt.setString(j, paramValue.toString());
	    			}
	    			//此处应该再加一个date判断
	    		}
	    	}
	    	//
	    	rs = pstmt.executeQuery();
	    	//接收返回值
	    	ResultSetMetaData rsmd = rs.getMetaData();
	    	while(rs.next()){
	    		Map<String, Object> dataMap = new HashMap<String, Object>(0);
	    		for(int i=1;i<=rsmd.getColumnCount();i++){
	    			dataMap.put(rsmd.getColumnName(i), rs.getObject(i));
	    		}
	    		resultList.add(dataMap);
	    	}
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    	System.out.println("数据连接异常！");
	    } catch (NullPointerException e) {
	    	e.printStackTrace();
	    	System.out.println("数据操作异常！");
	    } finally{
	    	closeAll(rs, pstmt, conn);
	  }
	  return resultList;
	}
	
	/**
	 * Transaction 事物控制
	 * @param slist
	 * @param olist
	 */
	@SuppressWarnings("resource")
	public static boolean Transaction(List<String> slist,List<List<Object>> olist) {
		if(slist ==null||slist.size()==0) {
			return false;
		}
		boolean flag = true;
		Connection conn = null;
		PreparedStatement stmt = null;      
		try {
			//获取数据库连接
			conn = DBUtils.getConnection();
			//开启事物
			conn.setAutoCommit(false);
			//获取数据库操作对象
			for (int i = 0;i< slist.size(); i++) {//循环SQL语句
				
				if(olist!=null&&olist.size()!=0){//判断集合是否为空
					stmt = conn.prepareStatement(slist.get(i));
					//遍历集合中的集合
		    		for(int j=0;j<olist.get(i).size();j++){//遍历参数
		    			if(olist.get(i)!=null&&olist.get(i).size()!=0) {//再判断参数是否为空
			    			//接收参数的值
			    			Object value = olist.get(i).get(j);
			    			//判断值是否为Integer类型
			    			int k=j+1;
			    			if("java.lang.Integer".equalsIgnoreCase(value.getClass().getName())){
			    				//如果是则将参数赋值
			    				stmt.setInt(k, Integer.parseInt(value.toString()));
			    			}else if("java.lang.String".equalsIgnoreCase(value.getClass().getName())){
			    				stmt.setString(k, value.toString());
			    			}
			    			//此处应该再加一个date判断
		    			}
		    		}
		    		stmt.addBatch();
		    		int[] result = stmt.executeBatch();
		    		for (int j : result) {
						if(j>0) {
							throw new Exception();
						}
					}
		    	}
			}
			conn.commit();//执行成功提交事物
			conn.setAutoCommit(true);
		} catch(Exception e) {
			System.out.println("出现异常");
			flag = false;
			//如果连接不为空 回滚事务
			if(conn != null) {
				try {
					System.out.println("回滚事物");
					conn.rollback();
				} catch(SQLException ex) {}
			}
			e.printStackTrace();
		} finally {
			//关闭
			closeAll(null, stmt, conn);
		}
		return flag;
	}
	
	
	
	/**
	 * closeAll 关闭所有连接
	 * @param ResultSet 结果集对象
	 * @param PreparedStatement 数据库操作对象
	 * @param Connection 数据库连接对象
	 * @return 没有返回值
	 */
	public static void closeAll(ResultSet rs,PreparedStatement stmt,Connection conn) {
		closeAll(rs,stmt);
        if (conn != null) { // 关闭数据库连接对象  
            try {  
                if (!conn.isClosed()) {  
                    conn.close();  
                }  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
	}
	
	/**
	 * closeAll 关闭所有连接
	 * @param ResultSet 结果集对象
	 * @param PreparedStatement 数据库操作对象
	 * @return 没有返回值
	 */
	public static void closeAll(ResultSet rs,PreparedStatement stmt) {
		// 7、关闭对象，回收数据库资源  
        if (rs != null) { //关闭结果集对象  
            try {  
                rs.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
        if (stmt != null) { // 关闭数据库操作对象  
            try {  
                stmt.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        } 
	}
	
	
	
	
	
	
	
	
}//类结尾











