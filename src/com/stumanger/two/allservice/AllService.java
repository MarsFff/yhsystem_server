package com.stumanger.two.allservice;

import java.util.List;

import com.stumanger.two.domain.Student;
import com.stumanger.two.domain.Teacher;

public interface AllService {
	// 添加学生的业务
	public String addStudent(int id, String name, double score);

	// 查询所有学生
	public List<Student> findAllStudent();

	// 排序查询
	public List<Student> sort(Boolean b);

	// 根据id查询学生
	public Student findById(int id);

	// 删除学生业务
	public String removeStudent(int id);

	// 修改学生成绩
	public String updatebyId(int id, double sscore);

	// 模糊查询学生
	public List<Student> vagueSelect(String sname);

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
