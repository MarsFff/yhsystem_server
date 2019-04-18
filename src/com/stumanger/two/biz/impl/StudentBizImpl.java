package com.stumanger.two.biz.impl;

import java.util.Collections;
import java.util.List;

import com.stumanger.two.biz.StudentBiz;
import com.stumanger.two.dao.StudentDao;
import com.stumanger.two.dao.impl.StudentDaoImpl;
import com.stumanger.two.domain.Student;
import com.stumanger.two.util.Rule;

public class StudentBizImpl implements StudentBiz {
	// 创建学生dao对象
	private StudentDao stuDao=new StudentDaoImpl();

	/*public StudentBizImpl() {
		super();
		// 多态
		this.stuDao = new StudentDaoImpl();
	}*/

	@Override
	public String addStudent(int id, String name, double score) {
		boolean b = this.stuDao.intsertStudent(new Student(id, name, score));
		return b ? "添加成功！" : "添加失败！";
	}

	@Override
	public List<Student> findAllStudent() {
		return this.stuDao.selectAllStudent();
	}

	@Override
	public List<Student> sort(Boolean b) {
		List<Student> list = this.stuDao.selectAllStudent();
		Collections.sort(list,new Rule(b));
		return list;
	}

	@Override
	public Student findById(int id) {
		return this.stuDao.selectById(id);
	}

	@Override
	public String removeStudent(int id) {
		/*boolean b = this.stuDao.delectById(id);
		return b?"删除成功！":"删除失败！";*/
		return this.stuDao.delectById(id)?"删除成功！":"删除失败！";
	}

	@Override
	public String updatebyId(int id, double sscore) {
		boolean b = this.stuDao.updatebyId(id, sscore);
		return b?"修改成功！":"修改失败！";
	}

	@Override
	public List<Student> vagueSelect(String sname) {
		List<Student> list = this.stuDao.vagueSelect(sname);
		return list;
	}

}
