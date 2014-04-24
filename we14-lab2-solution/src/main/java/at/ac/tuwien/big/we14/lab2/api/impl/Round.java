package at.ac.tuwien.big.we14.lab2.api.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import at.ac.tuwien.big.we14.lab2.api.Question;

public class Round {
	
	private List<Question> qlist = new LinkedList<Question>();
	private int pos;
	protected static Logger log = Logger.getLogger(Round.class);
	
	public Round(List<Question> questions){
		this.qlist = questions;
		pos = 0;
		log.info("Eine neue Runde wurde initialisiert");
	}
	public boolean hasNext(){
		if(pos < qlist.size()){
			log.info("Die pos("+pos+") ist kleiner als die Listenlänge("+qlist.size()+")");
			return true;
		}else{
			log.info("Die pos("+pos+") ist kleiner NICHT als die Listenlänge("+qlist.size()+")");
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
		log.info("Etwas ist schiefgegangen!");
		return null;
	}
	
	
}
