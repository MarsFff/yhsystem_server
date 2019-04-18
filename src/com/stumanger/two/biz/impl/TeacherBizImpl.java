package com.stumanger.two.biz.impl;

import java.util.List;

import com.stumanger.two.biz.TeacherBiz;
import com.stumanger.two.dao.TeacherDao;
import com.stumanger.two.dao.impl.TeacherDaoImpl;

import com.stumanger.two.domain.Student;
import com.stumanger.two.domain.Teacher;

public class TeacherBizImpl implements TeacherBiz {
	private TeacherDao teaDao;
	
	public TeacherBizImpl() {
		super();
		this.teaDao = new TeacherDaoImpl();
	}

	@Override
	public List<Teacher> selectAllteacher() {
		return teaDao.selectAllteacher();
	}

	@Override
	public Teacher selectByTaccount(String taccount) {
		return teaDao.selectByTaccount(taccount);
	}

	@Override
	public String updateTmesage(Teacher t,String taccount) {
		boolean b = this.teaDao.updateTmesage(t, taccount);
		return b?"修改成功！":"修改失败！";
	}

	@Override
	public List<Student> tSelectAllStudent() {
		return this.teaDao.tSelectAllStudent();
	}

	@Override
	public Student selectByid(int sid) {
		return this.teaDao.selectByid(sid);
	}

}
