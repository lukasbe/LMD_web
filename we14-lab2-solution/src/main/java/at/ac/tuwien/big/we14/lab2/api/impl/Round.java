package at.ac.tuwien.big.we14.lab2.api.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import at.ac.tuwien.big.we14.lab2.api.Question;

public class Round {
	
	private List<Question> qlist = new LinkedList<Question>();
	private int pos;
	
	public Round(List<Question> questions){
		this.qlist = questions;
		pos = 0;
	}
	public boolean hasNext(){
		if(pos < qlist.size()){
			return true;
		}else{
			return false;
		}		
	}
	public Question next(){
		Iterator<Question> it = qlist.iterator();
		int counter = 0;		
		while(it.hasNext()){
			if(counter == pos){
				pos++;
				return (Question) it.next();
			}else{
				it.next();
			}
			counter ++;
		}
		return null;
	}
	
	
}
