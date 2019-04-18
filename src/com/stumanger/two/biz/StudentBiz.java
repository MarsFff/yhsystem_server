package com.stumanger.two.biz;

import java.util.List;

import com.stumanger.two.domain.Student;

//业务层
public interface StudentBiz {
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

}
