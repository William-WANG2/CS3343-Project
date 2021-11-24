package junitTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import game.GREGame;
import gameObject.Character;
import gameObject.EnumVocabularyBook;
import gameObject.GameResult;
import gameObject.Map;
import gameObject.MapNode;
import scenes.LoginScene;
import scenes.PlayingScene;
import scenes.ResultScene;
import scenes.RuleScene;
import scenes.Scene;
import util.GameTimer;
import util.Vector2d;

public class Scene_exit_test {
//unit test
	//GREGame.setGameResult
	@Test
	public void test01() {
		GREGame g=new GREGame();
		GameResult r = new GameResult();
		g.setGameResult(r);
		assertEquals(true,r.equals(g.getGameResult()));
	}
	//GREGame.setBookType
	@Test
	public void test02() {
		GREGame g=new GREGame();
		EnumVocabularyBook b = EnumVocabularyBook.GRE;
		g.setBookType(b);
		assertEquals(true,b.equals(g.getBookType()));
	}
//integration test
	//GREGame.loadScene
	@Test
	public void test03() {
		GREGame g=new GREGame();
		Scene s = new LoginScene();
		g.loadScene(s);
		assertEquals(g,s.mApp);
	}
	//LoginScene.handleMouseClick
	@Test
	public void test04() throws InterruptedException {
		GREGame g=new GREGame();
		g.mouse.isClicked=true;
		g.mouse.mousePos = new Vector2d(477, 285);//click rule
		Scene s = new LoginScene();
		g.loadScene(s);
		Thread.sleep(400); //enter the timeElapsed branch
		GameTimer.getInstance().Tick();
		s.update();
		s.exit();
		assertEquals(GREGame.currScene.getClass(),(new RuleScene()).getClass());
	}
	@Test
	public void test05() {
		GREGame g=new GREGame();
		g.mouse.isClicked=true;
		g.mouse.mousePos = new Vector2d(477, 585);//click button
		Scene s = new LoginScene();
		g.loadScene(s);
		s.update();
		s.exit();
		assertEquals(GREGame.currScene.getClass(),(new PlayingScene()).getClass());
	}
	@Test
	public void test06() {
		GREGame g=new GREGame();
		g.mouse.isClicked=false;
		g.mouse.mousePos = new Vector2d(477, 585);//false
		Scene s = new LoginScene();
		g.loadScene(s);
		s.update();
		s.exit();
		assertEquals(GREGame.currScene.getClass(),(new LoginScene()).getClass());
	}
	//LoginScene.exit
	@Test
	public void test07() {
		GREGame g=new GREGame();
		g.mouse.isClicked=true;
		g.mouse.mousePos = new Vector2d(477, 285);//click rule
		Scene s = new LoginScene();
		g.loadScene(s);
		s.update();
		s.exit();
		assertEquals(GREGame.currScene.getClass(),(new RuleScene()).getClass());
	}
	@Test
	public void test08() {
		GREGame g=new GREGame();
		g.mouse.isClicked=true;
		g.mouse.mousePos = new Vector2d(477, 585);//click button
		Scene s = new LoginScene();
		g.loadScene(s);
		s.update();
		s.exit();
		assertEquals(GREGame.currScene.getClass(),(new PlayingScene()).getClass());
	}
	//PlayingScene.exit
	@Test
	public void test09() {
		GREGame g=new GREGame();
		g.setBookType(EnumVocabularyBook.GRE);
		Scene s = new PlayingScene();
		g.loadScene(s);
		
		Map map = Map.getInstance();
		map.initialize(5, 5, 100, 100, 50, 50, "res/words/test_alg.txt");
		MapNode[][] map2d = map.getMap();
		Character cxk = Character.getInstance();
		cxk.enter(map2d[2][2]);
		map2d[2][1].handleClickEvent(-22, 26);
		MapNode.setViewNodeBlock();
		map2d[2][3].handleClickEvent(74, 26);
		MapNode.setViewNodeBlock();
		map2d[1][1].handleClickEvent(-2, -22);
		MapNode.setViewNodeBlock();
		map2d[0][2].handleClickEvent(26, -70);
		MapNode.setViewNodeBlock();
		map2d[0][3].handleClickEvent(74, -70);
		MapNode.setViewNodeBlock();
		map2d[1][3].handleClickEvent(94, -22);
		MapNode.setViewNodeBlock();
		map2d[3][1].handleClickEvent(-2, 74);
		MapNode.setViewNodeBlock();
		map2d[3][2].handleClickEvent(46, 74);
		MapNode.setViewNodeBlock();
		Character.getInstance().recomputeShortestPath(false);
		Character.getInstance().recomputeShortestPath(false);
		Character.getInstance().recomputeShortestPath(true);
		map2d[1][2].handleClickEvent(46, -22);
		MapNode.setViewNodeBlock();
		map2d[2][2].handleClickEvent(26, 26);
		MapNode.setViewNodeBlock();
		
		s.update();
		s.exit();
		assertEquals(GREGame.currScene.getClass(),(new ResultScene()).getClass());
	}
	@Test
	public void test10() {
		GREGame g=new GREGame();
		g.setBookType(EnumVocabularyBook.GRE);
		Scene s = new PlayingScene();		
		g.loadScene(s);
		s.update();
		s.exit();
		assertEquals(EnumVocabularyBook.GRE, g.getBookType());
	}
	//RuleScene.exit
	@Test
	public void test11() {
		GREGame g=new GREGame();
		g.mouse.isClicked=true;
		g.mouse.mousePos = new Vector2d(1125, 660);//click button
		Scene s = new RuleScene();
		g.loadScene(s);
		s.update();
		s.update();
		s.exit();
		assertEquals(GREGame.currScene.getClass(),(new LoginScene()).getClass());
	}
	@Test
	public void test12() {
		GREGame g=new GREGame();
		g.mouse.isClicked=true;
		g.mouse.mousePos = new Vector2d(1115, 660);//click button
		Scene s = new RuleScene();
		g.loadScene(s);
		s.update();
		s.exit();
		assertEquals(GREGame.currScene.getClass(),(new RuleScene()).getClass());
	}
	//ResultScene.exit
	@Test
	public void test13() {
		GREGame g=new GREGame();
		g.mouse.isClicked=true;
		g.mouse.mousePos = new Vector2d(540, 480);//click button
		Scene s = new ResultScene();
		g.loadScene(s);
		s.update();
		s.exit();
		assertEquals(GREGame.currScene.getClass(),(new LoginScene()).getClass());
	}
	@Test
	public void test14() {
		GREGame g=new GREGame();
		g.mouse.isClicked=true;
		g.mouse.mousePos = new Vector2d(530, 480);//click button
		Scene s = new ResultScene();
		g.loadScene(s);
		s.update();
		s.exit();
		assertEquals(GREGame.currScene.getClass(),(new ResultScene()).getClass());
	}
}
