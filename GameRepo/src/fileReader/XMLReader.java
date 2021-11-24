package fileReader;


import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import gameObject.WordInfo;

public class XMLReader{
	/**
	 * read the XML file and store the information in an ArrayList, m and n are the
	 * row number and column number of the map. The function will randomly select
	 * m*n words and store them in the ArrayList
	 * 
	 * @throws ExMapExceedWordSize
	 **/
	public static ArrayList<WordInfo> convert(String path){
		ArrayList<WordInfo> wordlist = new ArrayList<WordInfo>();
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
		return wordlist;
	}
	
	public static NodeList getFile(String path) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		NodeList nl = null;
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(path);
			nl= document.getElementsByTagName("word");
		} catch (Exception e) {
		}
		return nl;
	}

}
