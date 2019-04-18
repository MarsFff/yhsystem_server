package com.stumanger.two.domain;


import java.io.Serializable;
import java.util.Scanner;

public class Student implements Comparable<Student>,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 123456789;
	private int snum;
	private String sname;
	private double sscore;
	
	public Student() {
		super();
	}

	
	public Student(int snum, String sname, double sscore) {
		super();
		this.snum = snum;
		this.sname = sname;
		this.sscore = sscore;
	}


	public int getSnum() {
		return snum;
	}


	public void setSnum(int snum) {
		this.snum = snum;
	}


	public String getSname() {
		return sname;
	}


	public void setSname(String sname) {
		this.sname = sname;
	}


	public double getSscore() {
		return sscore;
	}


	public void setSscore(double sscore) {
		this.sscore = sscore;
	}


	@Override
	public String toString() {
		return snum + "\t" + sname + "\t" + sscore ;
	}
	public String show() {
		// TODO Auto-generated method stub
		return snum + "#" + sname + "#" + sscore ;
	}

	@Override
	public int compareTo(Student o) {
//		if(this.id>o.getId()){
//			System.out.println(this.id);
//			return 1;
//		}else if(this.id<o.getId()){
//			return -1;
//		}
//		return 0;
		// TODO Auto-generated method stub
		if(this.sscore>o.getSscore()){
			System.out.println(this.sscore);
			return 1;
		}else if(this.sscore<o.getSscore()){
			return -1;
		}
		return 0;
	}

	

}
