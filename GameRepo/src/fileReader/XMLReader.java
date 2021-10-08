package fileReader;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import keyValue.*;

public class XMLReader {
	public static ArrayList<Info> convert(String path) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		ArrayList<Info> wordlist = new ArrayList<Info>();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(path);
			NodeList wl = document.getElementsByTagName("word");
			for(int i = 0; i<wl.getLength();i++) {
				Node w= wl.item(i);
				String d = "";
				String a = "";
				NodeList dl=w.getChildNodes();
				for(int j=0;j<dl.getLength();j++) {
					if(dl.item(j).getNodeName()=="define") {
						d = dl.item(j).getNodeValue();
					}
					else if(dl.item(j).getNodeName()=="answer") {
						a = dl.item(j).getNodeValue();
					}
				}
				WordInfo word = new WordInfo(d,a);
				wordlist.add(word);
			}		
		}catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return wordlist;
	}

}
