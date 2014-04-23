package at.ac.tuwien.big.we14.lab2.api;

import java.util.ArrayList;
import java.util.HashMap;

public interface Answer {

	/**
	 * ID setzen
	 * @param id
	 */
	public void setId(int id);
	
	/**
	 * ID bekommen
	 * @return int id
	 */
	public int getId();
	
	/**
	 * set Time
	 * @param time
	 */
	public void setTime(int time);
	
	/**
	 * Time holen
	 * @return int time
	 */
	public int getTime();
	
	/**
	 * Player setzen
	 * @param player
	 */
	public void setPlayer(String player);
	
	/**
	 * gets the player
	 * @return
	 */
	public String getPlayer();
	
	/**
	 * returns true if the anwer is correct.
	 * @param correct
	 * @return the Answer is correct (all coices are okay)
	 */
	public Boolean getIsCorrect(Boolean correct);
	
	/**
	 * Sets the Choices that have been transmitted in the post event. (all, true or false)
	 * @param tickedhackerl
	 * @return
	 */
	public void setTickedHackerl(HashMap<Choice,Boolean> tickedhackerl);
	
	/**
	 * Validiert mit einer ArrayList voller Choice Objecte welche die richtigen Antworten enthalten.
	 * @param correctChoices Arraylist
	 * @return allChoices are okay
	 */
	public Boolean validateWith(ArrayList<Choice> correctChoices);
	
	public Boolean getIsComputerCorrect();
	
	public int getComputerTime();
	
}
