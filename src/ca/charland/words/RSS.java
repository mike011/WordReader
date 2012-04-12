package ca.charland.words;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

/**
 * Represents the RSS feed.
 * 
 * @author mcharland
 * 
 */
class RSS {

	/** The link of the URL. */
	private URL _url;

	/** All the links to the articles. */
	private List<URL> _articles;

	/**
	 * Instantiates a new RSS.
	 * 
	 * @param articles
	 *            The HTML links to the articles
	 */
	RSS(URL url) {
		_url = url;
	}

	/**
	 * Gets the articles links from the URL.
	 * 
	 * @return The linked articles, will not return null, 0 if none found.
	 * @throws XMLStreamException
	 *             the xML stream exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	void readArticleURLs() throws XMLStreamException, IOException {
		_articles = new ArrayList<URL>();

		XMLInputFactory rss = XMLInputFactory.newInstance();
		InputStream in = _url.openStream();

		XMLEventReader eventReader = rss.createXMLEventReader(in);

		// Read the RSS
		boolean item = false;
		while (eventReader.hasNext()) {
			XMLEvent event = eventReader.nextEvent();
			if (event.isStartElement()) {

				// Only want links after item.
				if (event.asStartElement().getName().getLocalPart().equals("item")) {
					item = true;
				}

				// Are you at a link?
				if (item && event.asStartElement().getName().getLocalPart().equals("link")) {
					XMLEvent next = eventReader.nextEvent();
					Characters characters = next.asCharacters();
					String link = characters.getData();
					_articles.add(new URL(link));
				}
			}
		}
	}

	/**
	 * @Return All the articles.
	 */
	public List<Article> getAllArticles() {
		List<Article> result = new ArrayList<Article>();
		try {
			readArticleURLs();
		} catch (XMLStreamException e) {
			System.err.println("Problem reading articles urls");
		} catch (IOException e) {
			System.err.println("Problem reading articles urls");
		}

		for (URL url : _articles) {
			result.add(new Article(url));
		}
		return result;
	}

}
