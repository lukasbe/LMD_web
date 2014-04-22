package at.ac.tuwien.big.we14.lab2.api;

/**
 * Interface f�r die Erstellung der Fragen
 * @author Daniel
 *
 */
public interface QuestionGenerator {
	/**
	 * Diese Methode liefert aus der jeweiligen Kategorie eine neue Frage
	 * @return Frage mit den m�glichen falschen und richtigen Antworten
	 */
	public Question getQuestion();
	public Category getCategory();

}
