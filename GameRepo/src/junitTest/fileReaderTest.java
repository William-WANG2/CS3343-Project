package junitTest;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Test;
import org.w3c.dom.NodeList;

import exception.ExMapExceedWordSize;
import fileReader.ReaderFactory;
import fileReader.TxtReader;
import fileReader.XMLReader;
import gameObject.BoxController;
import gameObject.BoxMessage;
import gameObject.MapNode;
import gameObject.MapNodeInfo;
import keyValue.Info;
import util.Mouse;
import util.Key;

public class fileReaderTest {
	@Test
	public void test1() throws ExMapExceedWordSize {
		String p = "./res/word.xml";
		NodeList res = XMLReader.getFile(p);
		
		assertEquals(null,res);
		
	}
	@Test
	public void test2() throws ExMapExceedWordSize {
		String p = "./res/word.xml";
		ArrayList<Info> l = XMLReader.convert(p, 10, 10);
		String res = l.get(0).getAns();
		assertEquals(1,1);
	}
}
