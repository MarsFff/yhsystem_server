package com.stumanger.two.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	public static final String PACK="oracle.jdbc.driver.OracleDriver";
	private static final String URL="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	private static final String ACCOUNT="admin";
	private static final String PASSWORD="admin";
	Connection conn=null;
	Statement stmt=null;
	PreparedStatement pstmt=null;
	public DBUtil() {
		//进行conn的实例化操作 jdbc链接
		try {
			Class.forName(PACK).newInstance();
			 conn = DriverManager.getConnection(URL,ACCOUNT,PASSWORD);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//创建增删改的方法
	//1非预编译 不需要参数
	public int update(String sql) throws SQLException {
		stmt = conn.createStatement();
		return stmt.executeUpdate(sql);
	}
	//2预编译 需要参数进行？赋值
	public int update(String sql,Object...arr) throws SQLException {
		pstmt = conn.prepareStatement(sql);
		for (int i = 0; i < arr.length; i++) {
			pstmt.setObject((i+1), arr[i]);
		}
		int i = pstmt.executeUpdate();
		return i;
	}
//创建查询的方法
	//1非预编译
	public ResultSet query(String sql) throws SQLException {
		stmt=conn.createStatement();
		return stmt.executeQuery(sql);
	}
	//2预编译
	public ResultSet query(String sql,Object...arr) throws SQLException {
	 pstmt = conn.prepareStatement(sql);
	 for (int i = 0; i < arr.length; i++) {
		pstmt.setObject((i+1), arr[i]);
	}
	 return pstmt.executeQuery();
	}
	public void close() {
		try {
			if(pstmt!=null&&pstmt.isClosed())pstmt.close();
			if(stmt!=null&&stmt.isClosed())stmt.close();
			if(conn!=null&&conn.isClosed())conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
