package main.java;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WritetoXML {

	public static void writeOutput() {
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Literature_Count");
			doc.appendChild(rootElement);

			// staff elements
			Element staff = doc.createElement("Book");
			rootElement.appendChild(staff);

			// set attribute to staff element
			Attr attr = doc.createAttribute("age");
			attr.setValue("1");
			staff.setAttributeNode(attr);

			// set attribute to staff element
			Attr attr2 = doc.createAttribute("author");
			attr.setValue("1");
			staff.setAttributeNode(attr2);

			// set attribute to staff element
			Attr attr3 = doc.createAttribute("title");
			attr.setValue("1");
			staff.setAttributeNode(attr3);

			// set attribute to staff element
			Attr attr4 = doc.createAttribute("isbn");
			attr.setValue("1");
			staff.setAttributeNode(attr4);

			// unique words elements
			int total3 = Book.getUniqueWords();
			String total4 = Integer.toString(total3);
			Element unique_words = doc.createElement("unique_words");
			unique_words.appendChild(doc.createTextNode(total4));
			staff.appendChild(unique_words);

			// total count elements
			int total = Book.getNumWords();
			String total2 = Integer.toString(total);
			Element total_count = doc.createElement("total_count");
			total_count.appendChild(doc.createTextNode(total2));
			staff.appendChild(total_count);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("C:\\Users\\mwond\\Desktop/File.xml"));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
}
