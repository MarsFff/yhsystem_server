package com.stumanger.two.allservice.impl;

import java.util.List;

import com.stumanger.two.allservice.AllService;
import com.stumanger.two.biz.StudentBiz;
import com.stumanger.two.biz.TeacherBiz;
import com.stumanger.two.biz.impl.StudentBizImpl;
import com.stumanger.two.biz.impl.TeacherBizImpl;
import com.stumanger.two.domain.Student;
import com.stumanger.two.domain.Teacher;

public class allServiceImpl implements AllService {
	private StudentBiz stuBiz;
	private TeacherBiz teaBiz;

	public allServiceImpl() {
		super();
		this.stuBiz = new StudentBizImpl();
		this.teaBiz = new TeacherBizImpl();
	}

	@Override
	public String addStudent(int id, String name, double score) {
		// TODO Auto-generated method stub
		return this.stuBiz.addStudent(id, name, score);
	}

	@Override
	public List<Student> findAllStudent() {
		// TODO Auto-generated method stub
		return this.stuBiz.findAllStudent();
	}

	@Override
	public List<Student> sort(Boolean b) {
		// TODO Auto-generated method stub
		return this.sort(b);
	}

	@Override
	public Student findById(int id) {
		// TODO Auto-generated method stub
		return this.stuBiz.findById(id);
	}

	@Override
	public String removeStudent(int id) {
		// TODO Auto-generated method stub
		return this.stuBiz.removeStudent(id);
	}

	@Override
	public String updatebyId(int id, double sscore) {
		// TODO Auto-generated method stub
		return this.stuBiz.updatebyId(id, sscore);
	}

	@Override
	public List<Student> vagueSelect(String sname) {
		// TODO Auto-generated method stub
		return this.stuBiz.vagueSelect(sname);
	}

	@Override
	public List<Teacher> selectAllteacher() {
		// TODO Auto-generated method stub
		return this.teaBiz.selectAllteacher();
	}

	@Override
	public Teacher selectByTaccount(String taccount) {
		// TODO Auto-generated method stub
		return this.teaBiz.selectByTaccount(taccount);
	}

	@Override
	public String updateTmesage(Teacher t, String taccount) {
		// TODO Auto-generated method stub
		return this.teaBiz.updateTmesage(t, taccount);
	}

	@Override
	public List<Student> tSelectAllStudent() {
		// TODO Auto-generated method stub
		return this.teaBiz.tSelectAllStudent();
	}

	@Override
	public Student selectByid(int sid) {
		// TODO Auto-generated method stub
		return this.teaBiz.selectByid(sid);
	}

}
