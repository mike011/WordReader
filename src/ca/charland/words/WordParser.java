/**
 * Task:
Given an RSS feed URL, output the 50 most commonly used words throughout all of the linked articles, and their frequency.

Sample Input:
http://rss.cbc.ca/lineup/topstories.xml

Sample Output:
1 618 the
2 423 be
3 309 of
4 268 and
5 218 a
6 192 in
&hellip;
50 5 llama

(Note this is not actual output for the sample input.)

Deliverables:
● Executable Jar
● README with instructions for running it
● Source code

Guidelines:
● Feel free to use libraries for fetching & parsing if desired.
● Any source code not written by you should be clearly marked
● Document any significant assumptions or omissions

Goals:
● It works
● Code is logically organized, and readable
● Error handling is sensible
 */
package ca.charland.words;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.stream.XMLStreamException;

/**
 * The entry point into the application.
 * 
 * @author mcharland
 */
public class WordParser {

	/**
	 * Main entry point for application.
	 * 
	 * @param args
	 *            args[0] should be the URL of the RSS feed.
	 * @throws XMLStreamException
	 *             the XML stream exception
	 */
	public static void main(String args[]) throws XMLStreamException {

		URL url = getURL(args);
		if (url == null) {
			return;
		}

		WordParser main = new WordParser(url);
		main.readArticles();
		main.addUpAllWords();
		System.out.println(main.getTopFifty());
	}

	/**
	 * Simple helper method to check the URL.
	 * 
	 * @param args
	 *            The arguments passed into the application.
	 * @return The URL to use, if any problems encountered return null.
	 */
	static URL getURL(String[] args) {
		if (args == null || args.length == 0) {
			System.err.println("Please specify URL");
			return null;
		}

		URL url;
		try {
			url = new URL(args[0]);
		} catch (MalformedURLException e) {
			System.err.println("Could not read URL " + e.getMessage());
			return null;
		}
		return url;
	}

	/** The URL to read the articles from. */
	private URL _url;

	/** The words from the articles. */
	private Map<String, Integer> _words;

	/** The RSS containing all the articles. */
	private RSS _rss;

	/** All the articles. */
	private List<Article> _articles;

	/**
	 * Instantiates a new main.
	 * 
	 * @param string
	 *            the string
	 */
	public WordParser(URL url) {
		_url = url;
	}

	/**
	 * Read articles.
	 * 
	 */
	public void readArticles() {
		_rss = new RSS(_url);
		_articles = _rss.getAllArticles();

	}

	/**
	 * Combine all the articles to make a super list of all the words.
	 * 
	 * @return the all words
	 */
	void addUpAllWords() {
		_words = new TreeMap<String, Integer>();

		// Create a list of all the words.
		List<String> allWords = new ArrayList<String>();
		for (Article article : _articles) {
			allWords.addAll(article.getWords());
		}

		// Now see how many times the words are in the articles.
		for (String word : allWords) {
			int amount = 1;
			if (_words.containsKey(word)) {
				amount = _words.get(word) + 1;
			}
			_words.put(word, amount);

		}
	}

	/**
	 * Get the top fifty words list.
	 * 
	 * @return The top fifty words list.
	 */
	String getTopFifty() {

		StringBuffer output = new StringBuffer();

		List<WordFrequency> words = new ArrayList<WordFrequency>();
		for (String key : _words.keySet()) {
			words.add(new WordFrequency(key, _words.get(key)));
		}
		Collections.sort(words);

		int max = 50;
		int current = 1;

		for (WordFrequency word : words) {

			output.append(current).append(' ');
			output.append(word).append("\r\n");

			if (current >= max) {
				break;
			}

			++current;
		}
		return output.toString();
	}
}
