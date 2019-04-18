package com.stumanger.two.domain;

import java.io.Serializable;

public class Teacher implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 123;
private int tid;
private String taccount;
private String tpassword;
private String tname;

public Teacher() {
	super();
}

public Teacher(int tid, String taccount, String tpassword, String tname) {
	super();
	this.tid = tid;
	this.taccount = taccount;
	this.tpassword = tpassword;
	this.tname = tname;
}

public int getTid() {
	return tid;
}

public void setTid(int tid) {
	this.tid = tid;
}

public String getTaccount() {
	return taccount;
}

public void setTaccount(String taccount) {
	this.taccount = taccount;
}

public String getTpassword() {
	return tpassword;
}

public void setTpassword(String tpassword) {
	this.tpassword = tpassword;
}

public String getTname() {
	return tname;
}

public void setTname(String tname) {
	this.tname = tname;
}

@Override
public String toString() {
	return tid + "#" + taccount + "#" + tpassword + "#" + tname;
}

}
