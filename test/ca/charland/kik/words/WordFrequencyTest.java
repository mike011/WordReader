package ca.charland.kik.words;

import junit.framework.Assert;

import org.junit.Test;

/**
 * 
 * Tests for Word Frequency.
 * 
 * @author mcharland
 * 
 */
public class WordFrequencyTest {

	/**
	 * Test method for {@link ca.charland.kik.words.WordFrequency#compareTo(ca.charland.kik.words.WordFrequency)}.
	 */
	@Test
	public void testCompareToLess() {
		// Setup
		WordFrequency word = new WordFrequency("word", 5);
		WordFrequency wordTwo = new WordFrequency("wordtwo", 6);
		
		// Exercise
		int compareTo = word.compareTo(wordTwo);
		
		// Verify
		Assert.assertEquals("sort order is wrong", 1, compareTo);
	}

	/**
	 * Test method for {@link ca.charland.kik.words.WordFrequency#compareTo(ca.charland.kik.words.WordFrequency)}.
	 */
	@Test
	public void testCompareToGreater() {
		// Setup
		WordFrequency word = new WordFrequency("word", 6);
		WordFrequency wordTwo = new WordFrequency("wordtwo", 3);
		
		//Exercise
		int compareTo = word.compareTo(wordTwo);
		
		// Verify
		Assert.assertEquals("Sort order is wrong", -1, compareTo);
	}

	/**
	 * Test method for {@link ca.charland.kik.words.WordFrequency#compareTo(ca.charland.kik.words.WordFrequency)}.
	 */
	@Test
	public void testCompareToEquals() {
		// Setup
		WordFrequency word = new WordFrequency("word", 3);
		WordFrequency wordTwo = new WordFrequency("wordtwo", 3);
		
		// Exercise
		int compareTo = word.compareTo(wordTwo);
		
		// Verify
		Assert.assertEquals("Sort order is wrong", 0, compareTo);
	}

	/**
	 * Test method for {@link ca.charland.kik.words.WordFrequency#toString()}.
	 */
	@Test
	public void testToString() {
		// Setup
		WordFrequency word = new WordFrequency("word", 3);
		
		// Exercise
		String out = word.toString();
		
		// Verify
		Assert.assertEquals("Output is bad", "3 word", out);
	}

}
