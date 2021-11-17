package fileReader;

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
import gameObject.WordInfo;

public class XMLReader{
	/**
	 * read the XML file and store the information in an ArrayList, m and n are the
	 * row number and column number of the map. The function will randomly select
	 * m*n words and store them in the ArrayList
	 * 
	 * @throws ExMapExceedWordSize
	 **/
	public static ArrayList<WordInfo> convert(String path, int m, int n) throws ExMapExceedWordSize {
		ArrayList<WordInfo> wordlist = new ArrayList<WordInfo>();
		ArrayList<WordInfo> res = new ArrayList<WordInfo>();
		NodeList wl = getFile(path);
		for(int i=0;i<wl.getLength();i++) {
			Node node = wl.item(i);
			if(node.getNodeType()==Node.ELEMENT_NODE) {
				NodeList cl = node.getChildNodes();
				String def = "";
				String ans = "";
				for(int j=0;j<cl.getLength();j++) {
					Node d=cl.item(j);
					if(d.getNodeType()==Node.ELEMENT_NODE && d.getNodeName()=="define") {
						def= d.getTextContent();
					}
					if(d.getNodeType()==Node.ELEMENT_NODE && d.getNodeName()=="answer") {
						ans= d.getTextContent();
					}
				}
				wordlist.add(new WordInfo(def, ans));
			}
		}
		if (m * n > wordlist.size() + 1) {
			throw new ExMapExceedWordSize();
		}
		int startPosInList = new Random().nextInt(wl.getLength() + 1 - m * n);
		for (int i = startPosInList; i < startPosInList + m * n; i++) {
			res.add(wordlist.get(i));
		}
		return res;
	}
	
	public static NodeList getFile(String path) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(path);
			NodeList nl= document.getElementsByTagName("word");
			return nl;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
