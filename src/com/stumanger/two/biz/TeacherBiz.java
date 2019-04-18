package com.stumanger.two.biz;

import java.util.List;

import com.stumanger.two.domain.Student;
import com.stumanger.two.domain.Teacher;

public interface TeacherBiz {
	// 通过教师id查询教师
	public List<Teacher> selectAllteacher();

	// 通过教师账号查找教师
	public Teacher selectByTaccount(String taccount);

	// 教师修改自己信息
	public String updateTmesage(Teacher t, String taccount);

	// 教师查询所有学生信息
	public List<Student> tSelectAllStudent();

	// 教师根据id查询学生信息
	public Student selectByid(int sid);
}
