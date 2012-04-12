package ca.charland.kik.words;

import java.io.File;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import ca.charland.kik.words.Article;
import ca.charland.kik.words.RSS;

/**
 * Tests for RSS
 * 
 * @author mcharland
 */
public class RSSTest {

	/**
	 * Tests getting all the articles from the RSS.
	 */
	@Test
	public void testGetAllArticles() throws Exception {

		// Setup
		RSS main = new RSS(new File("test/res/sample_RSS.xml").toURI().toURL());

		// Exercise
		List<Article> linkedArticles = main.getAllArticles();

		// Verify
		Assert.assertNotNull("No articles created", linkedArticles);
		Assert.assertEquals("Wrong amount of articles found", 15, linkedArticles.size());
	}

}
