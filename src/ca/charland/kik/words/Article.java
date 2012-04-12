package ca.charland.kik.words;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.ElementIterator;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

import ca.charland.kik.words.utilities.StringParsing;

/**
 * Represents one article.
 * 
 * @author mcharland
 * 
 */
class Article {

	/** The URL. */
	private URL _url;

	/**
	 * Instantiates a new article with only the URL.
	 * 
	 * @param url
	 *            the URL
	 */
	Article(URL url) {
		_url = url;
	}

	/**
	 * Reads the article. Heavily sourced from <link>http://www.java2s.com/Code/Java/Development-Class/HTMLDocumentElementIteratorExample.htm</link>
	 * 
	 * @return The words of the article.
	 * @throws MalformedURLException
	 *             the malformed url exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws BadLocationException
	 *             the bad location exception
	 */
	String read() throws MalformedURLException, IOException, BadLocationException {
		StringBuffer result = new StringBuffer();
		URLConnection connection = _url.openConnection();
		InputStream is = connection.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);

		HTMLEditorKit htmlKit = new HTMLEditorKit();
		HTMLDocument htmlDoc = (HTMLDocument) htmlKit.createDefaultDocument();
		HTMLEditorKit.Parser parser = new ParserDelegator();
		HTMLEditorKit.ParserCallback callback = htmlDoc.getReader(0);
		parser.parse(br, callback, true);

		// Parse
		ElementIterator iterator = new ElementIterator(htmlDoc);
		Element element;
		while ((element = iterator.next()) != null) {
			AttributeSet attributes = element.getAttributes();
			Object name = attributes.getAttribute(StyleConstants.NameAttribute);
			if ((name instanceof HTML.Tag) && (name == HTML.Tag.P)) {
				// Build up content text as it may be within multiple elements
				StringBuffer text = new StringBuffer();
				int count = element.getElementCount();
				for (int i = 0; i < count; i++) {
					Element child = element.getElement(i);
					AttributeSet childAttributes = child.getAttributes();
					if (childAttributes.getAttribute(StyleConstants.NameAttribute) == HTML.Tag.CONTENT) {
						int startOffset = child.getStartOffset();
						int endOffset = child.getEndOffset();
						int length = endOffset - startOffset;
						text.append(htmlDoc.getText(startOffset, length));
					}
				}
				result.append(text.toString());
			}
		}
		return result.toString();
	}

	/**
	 * Gets the words. Returns an empty list if there were any problems gathering the information.
	 * 
	 * @return the words
	 */
	List<String> getWords() {

		String content = null;
		try {
			content = read();
		} catch (MalformedURLException e) {
			System.err.println("Could not read article");
		} catch (IOException e) {
			System.err.println("Could not read article");
		} catch (BadLocationException e) {
			System.err.println("Could not read article");
		}

		if (content == null) {
			return new ArrayList<String>();
		}
		return StringParsing.splitWords(content);
	}

}
