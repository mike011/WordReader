package ca.charland.words.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * The class StringParsing which provides simple parsing utilities.
 * 
 * @author mcharland
 */
public class StringParsing {

	/**
	 * Split the string.
	 * 
	 * @param content
	 *            the content
	 * @return the list
	 */
	public static List<String> splitWords(String content) {

		List<String> splits = new ArrayList<String>();

		Set<String> dictionary = null;
		try {
			dictionary = readFile("dictionary.txt");
		} catch (FileNotFoundException e) {
			System.err.println("Could not find dictionary file");
		} catch (IOException e) {
			System.err.println("Could not read dictionary file");
		}

		if (dictionary != null) {
			StringBuffer word = new StringBuffer();
			for (char c : content.toCharArray()) {

				if (Character.isLetter(c) || c == '\'') {
					word.append(c);
				} else {
					isWord(dictionary, word, splits);
					word = new StringBuffer();
				}
			}

			// Make sure what's left is a word.
			isWord(dictionary, word, splits);
		}
		return splits;
	}

	/**
	 * Check to see if the string passed in is a word.
	 * 
	 * @param dic
	 *            The dictionary of all the words to check against.
	 * @param word
	 *            the word
	 * @param words
	 *            A set of all the words
	 * 
	 * @return true, if successful
	 */
	private static void isWord(Set<String> dic, StringBuffer word, List<String> words) {

		String lowerCase = word.toString().toLowerCase();

		boolean isWord = false;
		if (lowerCase.length() > 0 && dic.contains(lowerCase)) {
			isWord = true;
		}

		if (isWord) {
			words.add(word.toString().toLowerCase());
		}

	}

	/**
	 * Reads a file.
	 * 
	 * @param name
	 *            the name
	 * @return the sets the
	 */
	private static Set<String> readFile(String name) throws FileNotFoundException, IOException {

		Set<String> result = new TreeSet<String>();

		InputStream is = null;
		BufferedReader dis = null;

		try {
			// is = new FileInputStream(file);
			is = StringParsing.class.getResourceAsStream(name);
			dis = new BufferedReader(new InputStreamReader(is));

			// Read the file line by line.
			String line = null;
			while ((line = dis.readLine()) != null) {
				result.add(line);
			}

		} finally {
			is.close();
			dis.close();
		}

		return result;
	}

}
