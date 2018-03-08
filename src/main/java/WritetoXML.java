package main.java;

import java.io.File;
import java.io.StringWriter;

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
			
			// Word elements
			Element Words = doc.createElement("Words");
			staff.appendChild(Words);

//			// unique words elements
//			int total3 = Book.getUniqueWords();
//			String total4 = Integer.toString(total3);
//			Attr unique_words = doc.createAttribute("unique_words");
//			unique_words.setValue(total4);
//			Words.setAttributeNode(unique_words);
//
//			// total count elements
//			int total = Book.getNumWords();
//			String total2 = Integer.toString(total);
//			Attr total_count = doc.createAttribute("total_count");
//			total_count.setValue(total2);
//			Words.setAttributeNode(total_count);

			// write the content into xml file
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "6");
			//initialize StreamResult with File object to save to file
			StreamResult result = new StreamResult(new StringWriter());
			DOMSource source = new DOMSource(doc);
			File desktop = new File(System.getProperty("user.home") + "/Desktop/newDictionary(rename).xml");
			result = new StreamResult(desktop);
			transformer.transform(source, result);


			System.out.println("File saved to: " +  desktop);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
}
