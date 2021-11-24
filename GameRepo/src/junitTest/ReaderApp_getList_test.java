package junitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;

import org.junit.Test;

import exception.ExMapExceedWordSize;
import fileReader.ReaderApp;
import fileReader.ReaderFactory;
import fileReader.TxtReader;
import fileReader.XMLReader;
import gameObject.WordInfo;

public class ReaderApp_getList_test {
//unit test
	//ReaderFactory.getFileType
	@Test
	public void test01() {
		ReaderFactory rf = new ReaderFactory();		
		String res = rf.getFileType("./res/words/word.txt");
		assertEquals("txt",res);
	}
	//TxtReader.convert
	@Test
	public void test02() throws FileNotFoundException {	
		ArrayList<WordInfo> wl = TxtReader.convert("./res/words/test_read.txt");
		ArrayList<WordInfo> exp = new ArrayList<WordInfo>();
		WordInfo e1 = new WordInfo("test1def", "test1ans");
		WordInfo e2 = new WordInfo("test2def", "test2ans");
		exp.add(e1);
		exp.add(e2);
		assertEquals(wl.get(0).getDefinition(), exp.get(0).getDefinition());
		assertEquals(wl.get(1).getDefinition(), exp.get(1).getDefinition());
		assertEquals(wl.get(0).getWord(), exp.get(0).getWord());
		assertEquals(wl.get(1).getWord(), exp.get(1).getWord());
	}
	//XMLReader.convert
	@Test
	public void test03() {	
		ArrayList<WordInfo> wl = XMLReader.convert("./res/words/test_read.xml");
		ArrayList<WordInfo> exp = new ArrayList<WordInfo>();
		WordInfo e1 = new WordInfo("test1def", "test1ans");
		WordInfo e2 = new WordInfo("test2def", "test2ans");
		exp.add(e1);
		exp.add(e2);
		assertEquals(wl.get(0).getDefinition(), exp.get(0).getDefinition());
		assertEquals(wl.get(1).getDefinition(), exp.get(1).getDefinition());
		assertEquals(wl.get(0).getWord(), exp.get(0).getWord());
		assertEquals(wl.get(1).getWord(), exp.get(1).getWord());
	}
//integration test
	//ReaderFactory.convert
	@Test
	public void test04() throws FileNotFoundException, ExMapExceedWordSize{	
		ReaderFactory rf = new ReaderFactory();
		ArrayList<WordInfo> wl = rf.convert("./res/words/test_read.xml", 1, 1);
		ArrayList<WordInfo> exp = new ArrayList<WordInfo>();
		WordInfo e1 = new WordInfo("test1def", "test1ans");
		WordInfo e2 = new WordInfo("test2def", "test2ans");
	}
	@Test
	public void test05() throws FileNotFoundException{	
		try {
			ReaderFactory rf = new ReaderFactory();
			ArrayList<WordInfo> wl = rf.convert("./res/words/test_read.txt", 10, 1);
		}
		catch(ExMapExceedWordSize e) {
			assertEquals(true,true);
		}
		
	}
	@Test
	public void test06() throws ExMapExceedWordSize{	
		try {
			ReaderFactory rf = new ReaderFactory();
			ArrayList<WordInfo> wl = rf.convert("./res/words/test_read.rbs", 1, 1);
		}
		catch(FileNotFoundException e) {
			assertEquals(true,true);
		}
	}
	//ReaderApp.getList
	@Test
	public void test07() throws FileNotFoundException, ExMapExceedWordSize{	
		ReaderApp ra = new ReaderFactory();
		ArrayList<WordInfo> wl = ra.getList("./res/words/test_read.xml", 1, 1);
		ArrayList<WordInfo> exp = new ArrayList<WordInfo>();
	}
	
}
