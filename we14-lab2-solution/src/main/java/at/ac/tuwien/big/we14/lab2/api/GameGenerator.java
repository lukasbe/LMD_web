package at.ac.tuwien.big.we14.lab2.api;

import java.util.HashMap;
import java.util.List;

import at.ac.tuwien.big.we14.lab2.api.impl.GameEntity;


public interface GameGenerator {
	
	// Erstellt ein neues Spiel mit rounds zufälligen Kategorien und questions zufälligen Fragen  
	public HashMap<String, List<Question>> generateGame(int rounds, int questioncount);
}
