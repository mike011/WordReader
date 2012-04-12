package ca.charland.words;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.swing.text.BadLocationException;

import junit.framework.Assert;

import org.junit.Test;

import ca.charland.words.Article;

/**
 * Tests for Article.
 * 
 * @author mcharland
 */
public class ArticleTest {

	/**
	 * Test getting the words.
	 * 
	 */
	@Test
	public void testGetWords() throws MalformedURLException, IOException, BadLocationException {
		
		// Setup
		Article a = new Article(new File("test/res/sample_article.html").toURI().toURL());
		a.read();
		
		// Exercise
		List<String> words = a.getWords();
		
		// Verify		
		Assert.assertNotNull(words);
		Assert.assertTrue(words.size() > 0);
	}
}
