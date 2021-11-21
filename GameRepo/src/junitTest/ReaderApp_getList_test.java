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
		boolean res = true;
		WordInfo e1 = new WordInfo("test1def", "test1ans");
		WordInfo e2 = new WordInfo("test2def", "test2ans");
		exp.add(e1);
		exp.add(e2);
		for(int i=0; i<wl.size();i++) {
			if(!(exp.get(i).getDefinition().equals(wl.get(i).getDefinition()))&&exp.get(i).getWord().equals(wl.get(i).getWord())) {
				res=false;
			}
		}
		assertEquals(true,res);
	}
	//XMLReader.convert
	@Test
	public void test03() {	
		ArrayList<WordInfo> wl = XMLReader.convert("./res/words/test_read.xml");
		ArrayList<WordInfo> exp = new ArrayList<WordInfo>();
		boolean res = true;
		WordInfo e1 = new WordInfo("test1def", "test1ans");
		WordInfo e2 = new WordInfo("test2def", "test2ans");
		exp.add(e1);
		exp.add(e2);
		for(int i=0; i<wl.size();i++) {
			if(!(exp.get(i).getDefinition().equals(wl.get(i).getDefinition()))&&exp.get(i).getWord().equals(wl.get(i).getWord())) {
				res=false;
			}
		}
		assertEquals(true,res);
	}
//integration test
	//ReaderFactory.convert
	@Test
	public void test04() throws FileNotFoundException, ExMapExceedWordSize{	
		ReaderFactory rf = new ReaderFactory();
		ArrayList<WordInfo> wl = rf.convert("./res/words/test_read.xml", 1, 1);
		ArrayList<WordInfo> exp = new ArrayList<WordInfo>();
		boolean res = true;
		WordInfo e1 = new WordInfo("test1def", "test1ans");
		WordInfo e2 = new WordInfo("test2def", "test2ans");
		exp.add(e1);
		exp.add(e2);
		for(int i=0; i<wl.size();i++) {
			if(!(exp.get(i).getDefinition().equals(wl.get(i).getDefinition()))&&exp.get(i).getWord().equals(wl.get(i).getWord())) {
				res=false;
			}
		}
		assertEquals(true,res);
	}
	@Test
	public void test05(){	
		try {
			ReaderFactory rf = new ReaderFactory();
			ArrayList<WordInfo> wl = rf.convert("./res/words/test_read.txt", 10, 1);
		}
		catch(ExMapExceedWordSize e) {
			assertEquals(true,true);
		}
		catch(FileNotFoundException e) {
			assertEquals(true,false);
		}
		
	}
	@Test
	public void test06(){	
		try {
			ReaderFactory rf = new ReaderFactory();
			ArrayList<WordInfo> wl = rf.convert("./res/words/test_read.rbs", 1, 1);
		}
		catch(FileNotFoundException e) {
			assertEquals(true,true);
		}
		catch(ExMapExceedWordSize e) {
			assertEquals(true,false);
		}
	}
	//ReaderApp.getList
	@Test
	public void test07() throws FileNotFoundException, ExMapExceedWordSize{	
		ReaderApp ra = new ReaderFactory();
		ArrayList<WordInfo> wl = ra.getList("./res/words/test_read.xml", 1, 1);
		ArrayList<WordInfo> exp = new ArrayList<WordInfo>();
		boolean res = true;
		WordInfo e1 = new WordInfo("test1def", "test1ans");
		WordInfo e2 = new WordInfo("test2def", "test2ans");
		exp.add(e1);
		exp.add(e2);
		for(int i=0; i<wl.size();i++) {
			if(!(exp.get(i).getDefinition().equals(wl.get(i).getDefinition()))&&exp.get(i).getWord().equals(wl.get(i).getWord())) {
				res=false;
			}
		}
		assertEquals(true,res);
	}
	
}
