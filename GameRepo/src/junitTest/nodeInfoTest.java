package junitTest;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Test;

import exception.ExMapExceedWordSize;
import fileReader.ReaderFactory;
import fileReader.TxtReader;
import fileReader.XMLReader;
import keyValue.Info;

public class nodeInfoTest {
	@Test
	public void test1() throws ExMapExceedWordSize {
		//ArrayList<Info> wordList = new ArrayList<Info>(5*5);
		//wordList = XMLReader.convert("./res/word.xml", 5, 5);
		//String res = XMLReader.convert("./res/word.xml", 5, 5);//wordList.get(1).getDefin();
		//assertEquals("b", res);
	}
	@Test
	public void test2() {
		ReaderFactory rf = new ReaderFactory();		
		String res = rf.getFileType("./res/word.txt");
		assertEquals("txt",res);
	}
	@Test
	public void test3() throws FileNotFoundException, ExMapExceedWordSize {
		ReaderFactory rf = new ReaderFactory();	
		ArrayList<Info> a = rf.convert("./res/word.txt", 0, 0);
		String res = a.get(0).getAns();
		assertEquals("a",res);
	}
	@Test
	public void test4() {
		
	}
}
