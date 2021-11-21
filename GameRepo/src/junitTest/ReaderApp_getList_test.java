package junitTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fileReader.ReaderFactory;

public class ReaderApp_getList_test {
//unit test
	//ReaderFactory.getFileType
	@Test
	public void test_01() {
		ReaderFactory rf = new ReaderFactory();		
		String res = rf.getFileType("./res/word.txt");
		assertEquals("txt",res);
	}
	//TxtReader.convert
	
	//XMLReader.convert

//integration test
	//ReaderFactory.convert
	
	//ReaderApp.getList
}
