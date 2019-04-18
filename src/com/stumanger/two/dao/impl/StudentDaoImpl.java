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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.stumanger.two.dao.StudentDao;
import com.stumanger.two.domain.Student;

public class StudentDaoImpl implements StudentDao {
	private BufferedReader in;
	private PrintWriter out;
	public static final String URL = "data/student.txt";

	@Override
	public boolean intsertStudent(Student stu) {
		try {
			out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(URL, true), "utf-8"));
			out.println(stu.show());
			out.flush();
			return true;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			if (out != null)
				out.close();
		}

	}

	@Override
	public List<Student> selectAllStudent() {
		List<Student> list = new ArrayList<>();
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(URL), "utf-8"));
			String str = "";
			while ((str = in.readLine()) != null) {
				String[] arr = str.split("#");
				list.add(new Student(Integer.parseInt(arr[0]), arr[1], Double.parseDouble(arr[2])));

			}
			return list;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Student selectById(int id) {
		// 调用自身查询方法
		List<Student> selectAllStudent = selectAllStudent();
		for (Student student : selectAllStudent) {
			if (id == student.getSnum()) {
				return student;
			}
		}
		return null;
	}

	@Override
	public boolean delectById(int id) {
		List<Student> list = selectAllStudent();
		/*
		 * for (Student student : selectAllStudent) { if(id==student.getSnum()){
		 * student. } }
		 */
		Iterator<Student> it = list.iterator();
		while (it.hasNext()) {
			Student stu = it.next();
			if (stu.getSnum() == id) {
				it.remove();
				break;
			}
		}
		File file = new File(URL);
		if (file.exists()) {
			file.delete();
		}
		for (Student student : list) {
			intsertStudent(student);
		}
		return true;
	}

	@Override
	public boolean updatebyId(int id,double  sscore) {
		Student s = this.selectById(id);
		String sname = s.getSname();
		this.delectById(id);
		Student stu=new Student(id, sname, sscore);
		try {
			out=new PrintWriter(new OutputStreamWriter(new FileOutputStream(URL,true), "utf-8"));
			out.println(stu.show());
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
	}

	@Override
	public List<Student> vagueSelect(String sname) {
		List<Student> list = this.selectAllStudent();
		List<Student>list2=new ArrayList<>();
		for (Student student : list) {
			String sname2 = student.getSname();
			for (int i = 0; i < sname2.length(); i++) {
				char[] ch=new char[1];
				char charAt = sname2.charAt(i);
				ch[0]=charAt;
				String str=new String(ch);
				if(str.equals(sname)){
					list2.add(student);
					break;
				}
			}
		}
		
		return list2;
	}

}
