package fileReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import exception.ExMapExceedWordSize;
import keyValue.*;

public class XMLReader {
	/**
	 * read the XML file and store the information in an ArrayList, m and n are the
	 * row number and column number of the map. The function will randomly select
	 * m*n words and store them in the ArrayList
	 * 
	 * @throws ExMapExceedWordSize
	 **/
	public static ArrayList<Info> convert(String path, int m, int n) throws ExMapExceedWordSize {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		ArrayList<Info> wordlist = new ArrayList<Info>();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			File dataFile = new File(path);
			Document document = db.parse(dataFile);
			NodeList wl = document.getElementsByTagName("word");
			if (m * n > wl.getLength() + 1) {
				throw new ExMapExceedWordSize();
			}
			int startPosInList = new Random().nextInt(wl.getLength() + 1 - m * n);
			for (int i = startPosInList; i < startPosInList + m * n; i++) {
				Node w = wl.item(i);
				String d = "";
				String a = "";
				NodeList dl = w.getChildNodes();
				for (int j = 0; j < dl.getLength(); j++) {
					if (dl.item(j).getNodeName() == "define") {
						d = dl.item(j).getNodeValue();
					} else if (dl.item(j).getNodeName() == "answer") {
						a = dl.item(j).getNodeValue();
					}
				}
				WordInfo word = new WordInfo(d, a);
				wordlist.add(word);
			}
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return wordlist;
	}

}