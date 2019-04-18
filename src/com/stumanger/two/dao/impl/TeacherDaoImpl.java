package com.stumanger.two.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.stumanger.two.dao.StudentDao;
import com.stumanger.two.dao.TeacherDao;
import com.stumanger.two.domain.Student;
import com.stumanger.two.domain.Teacher;
import com.stumanger.two.util.DBUtil;

public class TeacherDaoImpl implements TeacherDao {
	private StudentDao stuDao;
	private DBUtil db;
	/*public static final String PACK="oracle.jdbc.driver.OracleDriver";
	private static final String URL="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	private static final String ACCOUNT="admin";
	private static final String PASSWORD="admin";*/
	public TeacherDaoImpl() {
		super();
		this.stuDao = new StudentDaoImpl();
		this.db=new DBUtil();
	}

	@Override
	public List<Teacher> selectAllteacher() {
		return null;
	}

	@Override
	public Teacher selectByTaccount(String taccount) {
		String sql="select * from teacher where account=?";
		try {
			ResultSet rs = this.db.query(sql, taccount);
			if(rs.next()){
				return new Teacher(rs.getInt("id"),rs.getString("account"),rs.getString("password"),rs.getString("name"));
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.db.close();
		}
		return null;
		/*
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			conn = DriverManager.getConnection(URL,ACCOUNT,PASSWORD);
			String sql="select * from teacher where account=?";
			pstmt = conn.prepareStatement(sql);
			System.out.println(taccount+"==============");
			pstmt.setString(1, taccount);
			ResultSet rs = pstmt.executeQuery();
			System.out.println(taccount+"=============="+sql);
			if(rs.next()){
				System.out.println(rs.getInt("id")+ rs.getString("account"));
				return new Teacher(rs.getInt("id"), rs.getString("account"), rs.getString("password"), rs.getString("name"));
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null&&!conn.isClosed())conn.close();
				if(pstmt!=null&&!pstmt.isClosed())pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	*/}

	@Override
	public  boolean updateTmesage(Teacher t,String taccount) {
		String sql="update teacher set account=?,password=?,name=?where id=?";
		try {
			int i = this.db.update(sql,t.getTaccount(),t.getTpassword(),t.getTname(),t.getTid());
			return i>0?true:false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		/*
		List<Teacher> list = this.selectAllteacher();
		Iterator<Teacher> it = list.iterator();
		while(it.hasNext()){
			Teacher tea=it.next();
			if(taccount.equals(tea.getTaccount())){
				it.remove();
				list.add(t);
				break;
			}
		}
		File file = new File(URL);
		if (file.exists()) {
			file.delete();
		}
		try {
			out=new PrintWriter(new OutputStreamWriter(new FileOutputStream(URL,true), "utf-8"));
			for (Teacher teacher : list) {
				out.println(teacher.toString());
			}
			out.flush();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(out!=null)out.close();
		}

		return true;
	*/
	}

	@Override
	public List<Student> tSelectAllStudent() {
		return this.stuDao.selectAllStudent();
	}

	@Override
	public Student selectByid(int sid) {
		return this.stuDao.selectById(sid);
	}

}
