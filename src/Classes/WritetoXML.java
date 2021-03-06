package Classes;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WritetoXML extends Book {

	public static void writeOutput(ArrayList<Book> library, File file) {
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			String title = file.getName();
			int pos = title.lastIndexOf(".");
			if (pos > 0) {
				title = title.substring(0, pos);
			}
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement(title);
			rootElement.setAttribute("count", Integer.toString(library.size()));
			doc.appendChild(rootElement);

			for (int i = 0; i < library.size(); i++) {
				Book book = library.get(i);
				String title1 = book.getTitle();
				Map<String, Integer> uniqueWords = book.getUniqueWords();
				int uniqueCount = uniqueWords.size();
				int totalCount = book.getTotalWords();

				// Book Element (Root)
				Element Book = doc.createElement("Book");
				rootElement.appendChild(Book);

				// Complete Attribute
				Attr attr = doc.createAttribute("complete");
				attr.setValue(book.getComplete());
				Book.setAttributeNode(attr);

				// Age Attribute
				Attr attr1 = doc.createAttribute("age");
				attr1.setValue(book.getAge());
				Book.setAttributeNode(attr1);

				// Genre Attribute
				Attr attr6 = doc.createAttribute("genre");
				attr6.setValue(book.getGenre());
				Book.setAttributeNode(attr6);

				// Author Attribute
				Attr attr2 = doc.createAttribute("author");
				attr2.setValue(book.getAuthor());
				Book.setAttributeNode(attr2);

				// Title Attibute
				Attr attr3 = doc.createAttribute("title");
				attr3.setValue(title1);
				Book.setAttributeNode(attr3);

				// ISBN Attribute
				Attr attr4 = doc.createAttribute("isbn");
				attr4.setValue(book.getISBN());
				Book.setAttributeNode(attr4);

				// Word elements
				Element Words = doc.createElement("Words");
				Book.appendChild(Words);

				// unique words elements
				String words = Integer.toString(uniqueCount);
				Attr unique_words = doc.createAttribute("unique_words");
				unique_words.setValue(words);
				Words.setAttributeNode(unique_words);

				// total count elements
				String total = Integer.toString(totalCount);
				Attr total_count = doc.createAttribute("total_count");
				total_count.setValue(total);
				Words.setAttributeNode(total_count);

				Iterator<Entry<String, Integer>> it = uniqueWords.entrySet().iterator();
				while (it.hasNext()) {

					// Word elements
					Element W = doc.createElement("W");
					Book.appendChild(W);

					Entry<String, Integer> pair = it.next();

					// Count Attribute
					String freq11 = pair.getValue().toString();
					Attr frequency1 = doc.createAttribute("freq");
					frequency1.setValue(freq11);
					W.setAttributeNode(frequency1);

					// Frequency Attribute
					String freq1 = pair.getKey().toString();
					Attr frequency = doc.createAttribute("W");
					frequency.setValue(freq1);
					W.setAttributeNode(frequency);

					it.remove();

				}

			}
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			// initialize StreamResult with File object to save to file
			StreamResult result = new StreamResult(new StringWriter());
			DOMSource source = new DOMSource(doc);
			result = new StreamResult(file);
			transformer.transform(source, result);

			System.out.println("File saved to: " + file.getAbsolutePath());

		} catch (

		ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	public WritetoXML(File bookFile, String author, String ISBN, String title, String age, String genre,
			String complete, int numWords, Map<String, Integer> uniqueWords, Map<String, Integer> frequency) {
		super(bookFile, author, ISBN, title, age, numWords, uniqueWords, genre, complete);

	}
}
