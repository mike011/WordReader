/**
 * 
 */
package ca.charland.words.utilities;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import ca.charland.words.utilities.StringParsing;

/**
 * Tests for String Parser.
 * 
 * @author mcharlands
 * 
 */
public class StringParsingTest {

	/**
	 * Test method for {@link ca.charland.words.utilities.StringParsing#splitWords(java.lang.String)}. <br>
	 * Simplest possible test.
	 */
	@Test
	public void testSplitSimple() {
		
		// Setup
		String string = "simple";
		
		// Exercise
		List<String> split = StringParsing.splitWords(string);

		// Verify
		Assert.assertEquals("Only one string is expected", 1, split.size());
		Assert.assertEquals("String text is wrong", string, split.get(0));
	}

	/**
	 * Test method for {@link ca.charland.words.utilities.StringParsing#splitWords(java.lang.String)}.
	 */
	@Test
	public void testSplitEmpty() {
		
		// Setup
		String string = "";
		
		// Exercise.
		List<String> split = StringParsing.splitWords(string);

		// Verify
		Assert.assertEquals("No strings expected", 0, split.size());
	}

	/**
	 * Test method for {@link ca.charland.words.utilities.StringParsing#splitWords(java.lang.String)}.
	 */
	@Test
	public void testSplitTooShort() {
		
		// Setup
		String string = "e";
		
		// Exercise
		List<String> split = StringParsing.splitWords(string);

		// Verify
		Assert.assertEquals("No strings expected", 0, split.size());
	}

	/**
	 * Test method for {@link ca.charland.words.utilities.StringParsing#splitWords(java.lang.String)}.
	 */
	@Test
	public void testSplitSingleLetters() {
		
		// Setup
		String string = "a i";
		
		// Exercise
		List<String> split = StringParsing.splitWords(string);

		// Verify
		Assert.assertEquals("Two strings expected", 2, split.size());
		Assert.assertEquals("String text is wrong", "a", split.get(0));
		Assert.assertEquals("String text is wrong", "i", split.get(1));
	}

	/**
	 * Test method for {@link ca.charland.words.utilities.StringParsing#splitWords(java.lang.String)}.
	 */
	@Test
	public void testSplitTwoWords() {
		
		// Setup
		String string = "dog cat";
		
		// Exercise
		List<String> split = StringParsing.splitWords(string);

		// Verify
		Assert.assertEquals("Two words expected", 2, split.size());
		Assert.assertEquals("String text is wrong", "dog", split.get(0));
		Assert.assertEquals("String text is wrong", "cat", split.get(1));
	}

	/**
	 * Test method for {@link ca.charland.words.utilities.StringParsing#splitWords(java.lang.String)}.
	 */
	@Test
	public void testSplitTwoSameWords() {
		
		// Setup
		String string = "dog dog";
		
		// Exercise
		List<String> split = StringParsing.splitWords(string);

		// Verify
		Assert.assertEquals("Two words expected", 2, split.size());
		Assert.assertEquals("String text is wrong", "dog", split.get(0));
		Assert.assertEquals("String text is wrong", "dog", split.get(1));
	}

	/**
	 * Test method for {@link ca.charland.words.utilities.StringParsing#splitWords(java.lang.String)}.
	 */
	@Test
	public void testSplitTwoSameWordsDifferentCase() {
		
		// Setup
		String string = "dOg Dog";
		
		// Exercise
		List<String> split = StringParsing.splitWords(string);

		// Verify
		Assert.assertEquals("Two words expected", 2, split.size());
		Assert.assertEquals("String text is wrong", "dog", split.get(0));
		Assert.assertEquals("String text is wrong", "dog", split.get(1));
	}

	/**
	 * Test method for {@link ca.charland.words.utilities.StringParsing#splitWords(java.lang.String)}.
	 */
	@Test
	public void testSplitPunctuation() {
		
		// Setup
		String string = "couldn't";
		
		// Exercise
		List<String> split = StringParsing.splitWords(string);

		// Verify
		Assert.assertEquals("No words expected", 0, split.size());
	}

	/**
	 * Test method for {@link ca.charland.words.utilities.StringParsing#splitWords(java.lang.String)}.
	 */
	@Test
	public void testSplitPunctuations() {
		
		// Setup
		String[] punctuations = { ".", ";", "?", ":", "@", "#", "$", "%" };
		for (String punctuation : punctuations) {
			String string = "dog" + punctuation;
			
			// Exercise
			List<String> split = StringParsing.splitWords(string);

			// Verify
			Assert.assertEquals("One word expected", 1, split.size());
			Assert.assertEquals("String text is wrong", "dog", split.get(0));
		}
	}

	/**
	 * Test method for {@link ca.charland.words.utilities.StringParsing#splitWords(java.lang.String)}.
	 */
	@Test
	public void testSplitHyphenated() {
		
		// Setup
		String string = "low-budget";
		
		// Exercise
		List<String> split = StringParsing.splitWords(string);

		// Verify
		Assert.assertEquals("Two words expected", 2, split.size());
		Assert.assertEquals("String text is wrong", "low", split.get(0));
		Assert.assertEquals("String text is wrong", "budget", split.get(1));
	}
}
