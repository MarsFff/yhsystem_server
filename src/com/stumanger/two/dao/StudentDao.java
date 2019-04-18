package com.stumanger.two.dao;

import java.util.List;

import com.stumanger.two.domain.Student;

public interface StudentDao {
	// 创建一个添加学生方法
	public boolean intsertStudent(Student stu);

	// 查询所有学生
	public List<Student> selectAllStudent();

	// 通过id查询一个学生
	public Student selectById(int id);

	// 删除一个学生
	public boolean delectById(int id);
	//修改学生成绩
	public boolean updatebyId(int id,double sscore);
	//模糊查询学生
	public List<Student> vagueSelect(String sname);
}
