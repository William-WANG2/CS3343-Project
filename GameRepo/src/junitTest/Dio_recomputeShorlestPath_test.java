package junitTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import algorithm.ShortestPath;
import gameObject.Map;
import gameObject.MapNode;
import gameObject.MapNodeInfo;
import gameObject.WordInfo;
import util.Vector2d;
import gameObject.Board;
import gameObject.Character;

public class Dio_recomputeShorlestPath_test {
	//Unit Testing Begins
	//Map.getColRowCount
	@Test
	public void test01(){
		Map map = Map.getInstance();
		map.initialize(5, 5, 100, 100, 50, 50, "res/words/test_alg.txt");
		Vector2d vec = map.getColRowCount();
		assertEquals(5, vec.x);
		assertEquals(5, vec.y);
	}
	//Map.getDummy
	@Test
	public void test02(){
		Map map = Map.getInstance();
		map.initialize(5, 5, 100, 100, 50, 50, "res/words/test_alg.txt");
		MapNode node = map.getDummy();
	}
	//MapNode.getAdicency
	@Test
	public void test03(){
		Map map = Map.getInstance();
		map.initialize(5, 5, 100, 100, 50, 50, "res/words/test_alg.txt");
		MapNode[][] map2d = map.getMap();
		MapNode node = map2d[0][0];
		ArrayList<MapNode> adj = node.getAdjacency();
		assertEquals(3, adj.size());
		assertEquals(map2d[0][1], adj.get(1));
		assertEquals(map2d[1][0], adj.get(0));
		assertEquals(map.getDummy(), adj.get(2));
	}
	//MapNode.getNodeInformation
	@Test
	public void test04(){
		Map map = Map.getInstance();
		map.initialize(5, 5, 100, 100, 50, 50, "res/words/test_alg.txt");
		MapNode node = map.getDummy();
		MapNodeInfo info = node.getNodeInformation();
		assertEquals(0, info.abstractPos.x);
		assertEquals(0, info.abstractPos.y);
		assertEquals(0, info.displayPos.x, 0);
		assertEquals(0, info.displayPos.y, 0);
		WordInfo wordInfo = info.getWordInfo();
		assertEquals("", wordInfo.getDefinition());
		assertEquals("", wordInfo.getWord());
		assertEquals(0, wordInfo.getWordLength());
	}
	//Unit Testing Ends
	//Integration Testing Begins
	//ShortestPath()
	@Test
	public void test05(){
		Map map = Map.getInstance();
		map.initialize(5, 5, 100, 100, 50, 50, "res/words/test_alg.txt");
		ShortestPath shortestPath = new ShortestPath();
	}
	//MapNode.handleClickEvent()
	@Test
	public void test06(){
		Map map = Map.getInstance();
		map.initialize(5, 5, 100, 100, 50, 50, "res/words/test_alg.txt");
		MapNode[][] map2d = map.getMap();
		map2d[2][1].handleClickEvent(-22, 26);
		map2d[2][3].handleClickEvent(74, 26);
		map2d[1][1].handleClickEvent(-2, -22);
		map2d[1][2].handleClickEvent(46, -22);
		map2d[3][1].handleClickEvent(-2, 74);
		map2d[3][2].handleClickEvent(46, 74);
		
		map2d[1][1].handleClickEvent(1000, 1000);
		map2d[1][1].handleClickEvent(-2, -22);
		map2d[4][4].handleClickEvent(1000, 1000);
		Character cxk = Character.getInstance();
		cxk.enter(map2d[2][2]);
		map2d[2][2].handleClickEvent(26, 26);
		Board board = Board.getInstance();
		board.setWordInfo(map2d[4][4].getNodeInformation().getWordInfo());
		map2d[4][4].handleClickEvent(122, 122);
	}
}
