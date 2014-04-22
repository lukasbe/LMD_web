package at.ac.tuwien.big.we14.lab2.api;

/**
 * Interface für die Erstellung der Fragen
 * @author Daniel
 *
 */
public interface QuestionGenerator {
	/**
	 * Diese Methode liefert aus der jeweiligen Kategorie eine neue Frage
	 * @return Frage mit den möglichen falschen und richtigen Antworten
	 */
	public Question getQuestion();
	public Category getCategory();

}
