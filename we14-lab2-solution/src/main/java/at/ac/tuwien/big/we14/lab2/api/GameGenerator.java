package at.ac.tuwien.big.we14.lab2.api;


public interface GameGenerator {
	
	// Erstellt ein neues Spiel mit rounds zuf�lligen Kategorien und questions zuf�lligen Fragen  
	public void generateGame(int rounds, int questioncount);
}
