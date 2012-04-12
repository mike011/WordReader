package ca.charland.kik.words;

/**
 * A class that holds how frequent a word occurs.
 * 
 * @author mcharland.
 */
public class WordFrequency implements Comparable<WordFrequency> {

	/** The word. */
	private final String _word;

	/** The amount of times the word is shown. */
	private final int _amount;

	/**
	 * Instantiates a new word frequency.
	 * 
	 * @param word
	 *            The name of the word
	 * @param amount
	 *            The amount of times the word appears.
	 */
	public WordFrequency(String word, int amount) {
		_word = word;
		_amount = amount;
	}

	/** {@inheritDoc} */
	@Override
	public int compareTo(WordFrequency o) {
		int amount = o.getAmount();
		if (_amount > amount) {
			return -1;
		} else if (_amount < amount) {
			return 1;
		}
		return 0;
	}

	/**
	 * Gets the amount of times the word has been repeated.
	 * 
	 * @return the amount
	 */
	private int getAmount() {
		return _amount;
	}

	/** {@inheritDoc} */
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append(_amount).append(' ');
		output.append(_word);
		return output.toString();
	}
}