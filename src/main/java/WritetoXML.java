package main.java;

import java.io.File;
import java.io.StringWriter;
import java.util.Map;

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

	public static void writeOutput() {
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("This_Library");
			rootElement.setAttribute("count", Integer.toString(booksList.size()));
			doc.appendChild(rootElement);

			for (int i = 0; i < booksList.size(); i++) {
				Book book = booksList.get(i);
				String title = book.getTitle();
				Map<String, Integer> uniqueWords = book.getUniqueWords();
				int uniqueCount = uniqueWords.size();
				int totalCount = book.getTotalWords();

				// staff elements
				Element staff = doc.createElement("Book");
				rootElement.appendChild(staff);

				// set attribute to staff element
				Attr attr = doc.createAttribute("age");
				attr.setValue("1");
				staff.setAttributeNode(attr);

				// set attribute to staff element
				Attr attr2 = doc.createAttribute("author");
				attr2.setValue("1");
				staff.setAttributeNode(attr2);

				// set attribute to staff element
				Attr attr3 = doc.createAttribute("title");
				attr3.setValue(title);
				staff.setAttributeNode(attr3);

				// set attribute to staff element
				Attr attr4 = doc.createAttribute("isbn");
				attr4.setValue("1");
				staff.setAttributeNode(attr4);

				// Word elements
				Element Words = doc.createElement("Words");
				staff.appendChild(Words);

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
			}
			// write the content into xml file

			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "6");
			// initialize StreamResult with File object to save to file
			StreamResult result = new StreamResult(new StringWriter());
			DOMSource source = new DOMSource(doc);
			File desktop = new File(System.getProperty("user.home") + "/Desktop/newDictionary(rename).xml");
			result = new StreamResult(desktop);
			transformer.transform(source, result);

			System.out.println("File saved to: " + desktop);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	public WritetoXML(File bookFile, String author, int ISBN, String title, int age, int numWords,
			Map<String, Integer> uniqueWords, Map<String, Integer> frequency) {
		super(bookFile, author, ISBN, title, age, numWords, uniqueWords, frequency);
		// TODO Auto-generated constructor stub
	}
}
