package com.stumanger.two.control;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.stumanger.two.biz.StudentBiz;
import com.stumanger.two.biz.TeacherBiz;
import com.stumanger.two.biz.impl.StudentBizImpl;
import com.stumanger.two.biz.impl.TeacherBizImpl;
import com.stumanger.two.domain.Student;
import com.stumanger.two.domain.Teacher;
import com.stumanger.two.util.Rule;

//主要业务逻辑
public class ControlThread extends Thread {
	private Socket client;
	// 业务层对象
	private StudentBiz stuBiz;
	private TeacherBiz teaBiz;
	private Scanner in;
	private PrintWriter out;

	public ControlThread(Socket client) {
		super();
		this.client = client;
		this.stuBiz = new StudentBizImpl();
		this.teaBiz = new TeacherBizImpl();
		this.getInAndOut();
	}

	private void getInAndOut() {
		try {
			in = new Scanner(client.getInputStream());
			out = new PrintWriter(client.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 创建接收整数、接收字符串、发送整数 、发送字符串方发
	private int getInt() {
		/* return in.nextInt(); */
		return Integer.parseInt(in.nextLine());
	}

	private String getString() {
		return in.nextLine();
	}

	private void sendString(String msg) {
		out.println(msg);
		out.flush();
	}

	private void sendBoolean(boolean b) {
		out.println(b);
		out.flush();
	}

	private double getDouble() {
		return Double.parseDouble(in.nextLine());
	}

	// 发送对象的方法
	private void sendObject(Object obj) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
			oos.writeObject(obj);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		String taccount = this.getString();
		String tpassword = this.getString();
		// System.out.println(taccount+"\t"+tpassword);
		if (this.selectTeaByAccount(taccount, tpassword)) {
			// selectTeaByAccount(taccount, tpassword);
			while (true) {

//				System.out.println("==++++++++++++==");
				int select;
				try {
					select = this.getInt();
//					System.out.println(select);
//					System.out.println("=============");
					if (select == 0) {
						System.out.println(this.client.getInetAddress().getHostAddress() + "已断开！");
						// System.exit(0);
						break;
					} else if (select == 1) {
						this.addstudent();

					} else if (select == 2) {
						this.showStudent();
					} else if (select == 3) {
						this.sortStudent();
					} else if (select == 4) {
						this.deleteStudent();
					} else if (select == 5) {
						this.updatestuscore();
					} else if (select == 6) {
						this.lookmyself();
					} else if (select == 7) {
						this.updateteacher();
					}else if(select==8){
						this.vagueselectBysname();
					}
				} catch (Exception e) {
					System.out.println(client.getInetAddress() + "已断开链接");
					e.printStackTrace();
					break;
				}

			}
		}
	}

	// 老师查看个人信息
	private Teacher lookmyself() {
		System.out.println("-----");
		String s = this.getString();
		Teacher tea = this.teaBiz.selectByTaccount(s);
		this.sendString(tea.toString());
		return tea;
	}

	// 修改老师个人信息
	private void updateteacher() {
		String taccount = this.getString();
		String tpassword = this.getString();
		String tname = this.getString();
		Teacher tea = this.teaBiz.selectByTaccount(taccount);
		int tid = tea.getTid();
		if ("y".equals(this.getString())) {
			String updateTmesage = teaBiz.updateTmesage(new Teacher(tid, taccount, tpassword, tname), taccount);
			this.sendString(updateTmesage);
		} else {
			this.sendString("已取消！");
		}

	}

	// 根据账户查找老师
	private boolean selectTeaByAccount(String taccount, String tpassword) {
		Teacher teacher = this.teaBiz.selectByTaccount(taccount);
		// System.out.println(teacher);
	 System.out.println(teacher);
		if (teacher != null && teacher.getTpassword().equals(tpassword)) {
			System.out.println("登陆成功！--");
			this.sendBoolean(true);
			return true;

		} else {
			System.out.println("账号或密码有误！++");
			this.sendBoolean(false);
			return false;
		}
	}

	// 添加学生
	private void addstudent() {
//		System.out.println("------------------------");
		String s = this.getString();
		// System.out.println("====================");
		String[] split = s.split("#");
		Student stu = this.stuBiz.findById(Integer.parseInt(split[0]));
		if (stu != null) {
			this.sendString("该学生已存在，添加失败！");
			return;
		}
		// System.out.println("22222222222222222222222");
		this.sendString(this.stuBiz.addStudent(Integer.parseInt(split[0]), split[1], Double.parseDouble(split[2])));
	}

	// 显示学生信息
	private void showStudent() {
		List<Student> list = this.stuBiz.findAllStudent();
		this.sendObject(list);

	}

	// 学生信息排序
	private void sortStudent() {
		List<Student> arr = this.stuBiz.findAllStudent();
		int i = this.getInt();
		if (i == 1) {
			Collections.sort(arr, new Rule(true));
		} else {
			Collections.sort(arr, new Rule(false));
		}
		this.sendObject(arr);
	}

	// 删除学生信息
	private void deleteStudent() {
		int id = this.getInt();
		Student stu = this.stuBiz.findById(id);
		if (stu == null) {
			return;
		}
		this.sendObject(stu);
		String s = this.getString();
		String result;
		if ("y".equals(s)) {
			result = this.stuBiz.removeStudent(id);
		} else {
			result = "取消删除！";
		}
		this.sendString(result);
	}

	// 修改学生成绩
	private void updatestuscore(){
		int snum = getInt();
		Student stu = this.stuBiz.findById(snum);
		sendObject(stu);
		double sscore = this.getDouble();
	if("y".equals(getString())){
		String updatebyId = this.stuBiz.updatebyId(snum, sscore);
		System.out.println("修改成功！");
		sendString(updatebyId);
	}	
	}
	//模糊查询学生
	private void vagueselectBysname(){
		String sname = getString();
		List<Student> list = this.stuBiz.vagueSelect(sname);
		sendObject(list);
	}
}
