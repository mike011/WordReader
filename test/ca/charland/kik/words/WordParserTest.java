package ca.charland.kik.words;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Tests for WordParser.
 * 
 * @author mcharland
 * 
 */
public class WordParserTest {

	/**
	 * Test method for {@link ca.charland.kik.words.WordParser#getURL(java.lang.String[])}.
	 */
	@Test
	public void testGetURLNull() {
		Assert.assertNull("No URL expected", WordParser.getURL(null));
	}

	/**
	 * Test method for {@link ca.charland.kik.words.WordParser#getURL(java.lang.String[])}.
	 */
	@Test
	public void testGetURLEmpty() {
		Assert.assertNull("No URL expected", WordParser.getURL(new String[] {}));
	}

	/**
	 * Test method for {@link ca.charland.kik.words.WordParser#getURL(java.lang.String[])}.
	 */
	@Test
	public void testGetURL() {
		// Setup
		String string = "http://rss.cbc.ca/lineup/topstories.xml";
		
		// Exercise
		URL url = WordParser.getURL(new String[] { string });
		
		// Verify
		Assert.assertNotNull("URL expected", url);
		Assert.assertEquals("URL expected", string, url.toString());
	}

	/**
	 * Test method for {@link ca.charland.kik.words.WordParser#getTopFifty()}.
	 * 
	 * @throws MalformedURLException
	 */
	@Test
	public void testGetTopFifty() throws MalformedURLException {
		// Setup
		WordParser wp = new WordParser(new File("test/res/sample_RSS.xml").toURI().toURL());
		wp.readArticles();
		wp.addUpAllWords();
		
		// Exercise
		String topFifty = wp.getTopFifty();
		
		// Verify
		Assert.assertNotNull("nothing returned", topFifty);
		Assert.assertTrue("nothing returned", topFifty.length() > 0);
	}
}