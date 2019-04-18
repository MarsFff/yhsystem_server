package com.stumanger.two.util;

import java.util.Comparator;

import com.stumanger.two.domain.Student;

public class Rule implements Comparator<Student>{
	private boolean flag;
	
	public Rule(boolean flag) {
		super();
		this.flag = flag;
	}

	@Override
	public int compare(Student o1, Student o2) {
		if(flag){
			if(o1.getSscore()>o2.getSscore()){
				return 1;
			}else if(o1.getSscore()<o2.getSscore()){
				return -1;
			}
		}else{
			if(o1.getSscore()>o2.getSscore()){
				return -1;
			}else if(o1.getSscore()<o2.getSscore()){
				return 1;
			}
		}
		
		
		return 0;
	}

	
}
