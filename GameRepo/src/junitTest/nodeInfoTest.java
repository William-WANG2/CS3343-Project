package junitTest;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Test;

import exception.ExMapExceedWordSize;
import fileReader.ReaderFactory;
import fileReader.TxtReader;
import fileReader.XMLReader;
import gameObject.BoxController;
import gameObject.BoxMessage;
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
		BoxMessage bm = new BoxMessage("aaaaaaa",1);
		String res=bm.getMessage();
		assertEquals("Please input your answer for the blank: aaaaaa_",res);
	}
	@Test
	public void test5() {
		BoxController bc = BoxController.getInstance();
		bc.update("aaaaaaa",1);
		boolean res = bc.checkInput();
		assertEquals(true,res);
	}
	@Test
	public void test6() {
		BoxController bc = BoxController.getInstance();
		char res = bc.getUsrIn();
		assertEquals('a',res);
	}
}
