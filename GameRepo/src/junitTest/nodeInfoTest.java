package junitTest;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Test;

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
		ArrayList<WordInfo> a = rf.convert("./res/word.txt", 0, 0);
		String res = a.get(0).getWord();
		assertEquals("a",res);
	}
	@Test
	public void test4() {
		//BoxMessage bm = new BoxMessage("aaaaaaa",1);
		//String res=bm.getMessage();
		//assertEquals("Please input your answer for the blank: aaaaaa_",res);
	}
	@Test
	public void test5() {
		//Board bc = Board.getInstance();
		//bc.update("d",1);
		//boolean res = bc.checkInput();
		//assertEquals(true,res);
	}
	@Test
	public void test6() {
		Board bc = Board.getInstance();
		//bc.update("aaaaaaa",1);
		//boolean res = bc.checkInput();
		//assertEquals(false,res);
	}
//	@Test
//	public void test7() {
//		class stub extends MapNode{
//			private int clickedTime = 0;
//			private MapNodeInfo info;
//			public stub(float x, float y, float r, int m, int n, Info gre) {
//				super(x, y, r, m, n, gre);
//			}
//			@Override
//			public void update(Mouse mouse, Key key) {
//				boolean isInGeo= Math.pow(mouse.mousePos.x - info.displayPos.y - info.radius,2) + Math.pow(mouse.mousePos.y - info.displayPos.x - info.radius, 2) < Math.pow(info.radius, 2);
//				if(isInGeo && info.blocked==false) {//add restore the click if click another one
//					if(clickedTime==1) {
//						if(BoxController.getInstance().checkInput()) {
//							info.blocked = true;
//						}
//						else {
//							mouse.mouseClicked = false;
//						}
//					}
//					else {
//						clickedTime=1;
//						//BoxController.getInstance().update(info.greInfo.getDefin(), 0);
//					}
//				}
//			}
//		}
//		MapNode mn = new stub(0, 0, 0, 0, 0, null);
//		Mouse ms = new Mouse();
//		BoxController bc = BoxController.getInstance();
//		//bc.update("aaaaaaa",1);
//		//mn.update(ms);
//		boolean res = bc.checkInput();
//		assertEquals(false,res);
//	}
}
