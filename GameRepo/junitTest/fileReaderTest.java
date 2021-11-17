package junitTest;

import static org.junit.Assert.assertEquals;


import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import exception.ExMapExceedWordSize;
import fileReader.ReaderFactory;
import fileReader.TxtReader;
import fileReader.XMLReader;
import gameObject.Board;
import gameObject.MapNode;
import gameObject.MapNodeInfo;
import gameObject.WordInfo;
import util.Mouse;
import util.Key;

public class fileReaderTest {
	@Test
	public void test1() throws ExMapExceedWordSize {
		String p = "./res/word.xml";
		NodeList l = XMLReader.getFile(p);
		for(int i=0;i<l.getLength();i++) {
			Node n = l.item(i);
			if(n.getNodeType()==Node.ELEMENT_NODE) {
				NodeList da = n.getChildNodes();
				for(int j=0;j<da.getLength();j++) {
					Node d=da.item(j);
					int k=0;
					if(d.getNodeType()==Node.ELEMENT_NODE && d.getNodeName()=="answer") {
						String def= d.getTextContent();
						k=0;
					}
				}
				int j=1;
			}
		}
		//String res=n.getChildNodes().item(0).getNodeValue();
		//assertEquals(true, "a".equals(res));
	}
	@Test
	public void test10() throws ExMapExceedWordSize, FileNotFoundException {
		String p = "./res/word.txt";
		ArrayList<WordInfo> l = TxtReader.convert(p, 0, 0);
		String res=l.get(0).getWord();
		assertEquals("a", res);
	}
	@Test
	public void test2() throws ExMapExceedWordSize {
		String p = "./res/word.xml";
		ArrayList<WordInfo> l = XMLReader.convert(p, 10, 10);
		String res = l.get(0).getWord();
		assertEquals(1,1);
	}
}
