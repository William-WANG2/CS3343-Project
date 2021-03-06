package junitTest;

import static org.junit.Assert.assertEquals;

import java.awt.event.KeyEvent;
import java.util.LinkedList;

import org.junit.Test;

import game.GREGame;
import game.GameSettingConstants;
import gameObject.Board;
import gameObject.Character;
import gameObject.EnumVocabularyBook;
import gameObject.GameButton;
import gameObject.GameResult;
import gameObject.Map;
import gameObject.MapNode;
import gameObject.MusicController;
import gameObject.WordInfo;
import scenes.PlayingScene;
import scenes.Scene;
import util.GameTimer;
import util.Key;
import util.Music;
import util.Vector2d;

public class Scene_update_test { 
	// Unit testing begin
	// GameResult.setIsWin
	@Test
	public void test01() {
		GameResult gameResult = new GameResult();
		gameResult.setIsWin(true);
		boolean res = gameResult.isGameWin();
		assertEquals(true, res);
	}
	
	// Character.isActive
	@Test
	public void test02() {
		Character character = Character.getInstance();
		character.enter(new MapNode(0, 0, 0, 0, 0, null));
		boolean res = character.isAlive();
		assertEquals(true, res);
	}
	
	// GameButton.update
	@Test
	public void test03() {
		GameButton gb = new GameButton("res/Button/Rule.png", "res/Button/Rule2.png", 0, 0, 2, 2);
		Vector2d mouse = new Vector2d(1, 1);
		gb.handleEvent(mouse);
		gb.update();
	}
	@Test
	public void test04() {
		GameButton gb = new GameButton("res/Button/Rule.png", "res/Button/Rule2.png", 0, 0, 2, 2);
		gb.update();
	}
	
	// MapNode.isViewExist
	@Test
	public void test05() {
		MapNode mn = new MapNode(0, 0, 2, 2, 2, new WordInfo("",""));
		mn.handleClickEvent(1, 1);
		boolean res = MapNode.isViewExist();
		assertEquals(true, res);
	}
	@Test
	public void test06() {
		MapNode mn = new MapNode(0, 0, 0, 0, 0, new WordInfo("",""));
		mn.handleClickEvent(1, 1);
		boolean res = MapNode.isViewExist();
		assertEquals(true, res);
	}
	
	// Board.handleInputDelete
	@Test
	public void test07() {
		Board board = Board.getInstance();
		board.handleInputDelete();
	}
	@Test
	public void test08() {
		Board board = Board.getInstance();
		board.setWordInfo(new WordInfo("aaaaaaaa", "aaaaaaaa"));
		board.handleInputLetter('a');
		board.handleInputLetter('a');
		board.handleInputDelete();
	}
	
	// MusicController.soundEffectLose
	@Test
	public void test10() {
		MusicController mc = MusicController.getInstance();
		mc.soundEffectLose();
	}
	
	// Board.handleInputFinish
	@Test
	public void test11() {
		Board board = Board.getInstance();
		board.handleInputFinish();
	}
	
	// MapNode.isBorder
	@Test
	public void test12() {
		MapNode mn = new MapNode(0, 0, 0, 0, 0, new WordInfo("",""));
		mn.addAdj(mn);
		boolean res = mn.isBorder();
		assertEquals(false, res);
	}

	// GameTimer.DeltaTime
	@Test
	public void test13() {
		GameTimer gt = GameTimer.getInstance();
		gt.Tick();
		gt.DeltaTime();
	}
	@Test
	public void test14() {
		GameTimer gt = GameTimer.getInstance();
		gt.Stop();
		gt.Stop();
		gt.Tick();
		assertEquals(0, gt.DeltaTime());
	}
	
	// EnumVocabularyBook.IntToWordType
	@Test
	public void test15() {
		String[] arr = new String[5];
		arr[0] = "res/words/PRIMARY.xml";
		arr[1] = "res/words/IELTS.xml";
		arr[2] = "res/words/TOFEL.xml";
		arr[3] = "res/words/GRE.xml";
		assertEquals(EnumVocabularyBook.getWordTypePath(EnumVocabularyBook.IntToWordType(0)), arr[0]);
		assertEquals(EnumVocabularyBook.getWordTypePath(EnumVocabularyBook.IntToWordType(1)), arr[1]);
		assertEquals(EnumVocabularyBook.getWordTypePath(EnumVocabularyBook.IntToWordType(2)), arr[2]);
		assertEquals(EnumVocabularyBook.getWordTypePath(EnumVocabularyBook.IntToWordType(3)), arr[3]);
		EnumVocabularyBook.IntToWordType(4);
		EnumVocabularyBook.getWordTypePath(EnumVocabularyBook.NULL);
	}
	
	// WordInfo.getWord
	@Test
	public void test16() {
		WordInfo wi = new WordInfo("test", "test");
		assertEquals("test", wi.getWord());
	}
	
	// MapNodeInfo.getWordInfo
	@Test
	public void test17() {
		GameButton gb = new GameButton("res/Button/Rule.png", "res/Button/Rule2.png", 1, 1, 2, 2);
		Vector2d mouse = new Vector2d(0, 0);
		gb.handleEvent(mouse);
		assertEquals(false, gb.isClicked());
	}
	@Test
	public void test18() {
		GameButton gb = new GameButton("res/Button/Rule.png", "res/Button/Rule2.png", 1, 1, 2, 2);
		Vector2d mouse = new Vector2d(5, 5);
		gb.handleEvent(mouse);
		assertEquals(false, gb.isClicked());
	}
	@Test
	public void test19() {
		GameButton gb = new GameButton("res/Button/Rule.png", "res/Button/Rule2.png", 1, 1, 2, 2);
		Vector2d mouse = new Vector2d(2, 0);
		gb.handleEvent(mouse);
		assertEquals(false, gb.isClicked());
	}
	@Test
	public void test20() {
		GameButton gb = new GameButton("res/Button/Rule.png", "res/Button/Rule2.png", 1, 1, 2, 2);
		Vector2d mouse = new Vector2d(2, 5);
		gb.handleEvent(mouse);
		assertEquals(false, gb.isClicked());
	}
	
	// MusicController.soundEffectExit
	@Test
	public void test21() {
		MusicController music = MusicController.getInstance();
		music.soundEffectWin();
		music.soundEffectLose();
		music.soundEffectExit();
	}
	
	// Music.loop
	@Test
	public void test22() {
		Music good = new Music("res/music/good.wav", 0);
		good.loop();
		good.close();
	}
	
	//Integration Testing Begin
	//PlayingScene.handleMouseClick
	@Test
	public void test23() {
		GREGame g=new GREGame();
		g.setBookType(EnumVocabularyBook.GRE);
		g.setBookType(EnumVocabularyBook.GRE);
		g.mouse.isClicked=true;
		Scene s = new PlayingScene();
		Map map = Map.getInstance();
		map.initialize(5, 5, 100, 100, 50, 50, "res/words/test_alg.txt");
		g.loadScene(s);
		s.update();
	}
	// Board.handleInput
	@Test
	public void test24() {
		Board board = Board.getInstance();
		board.setWordInfo(new WordInfo("aaaaaaaa", "aaaaaaaa"));
		board.handleInputLetter('a');
		board.handleInputLetter('a');
		board.handleInputLetter('a');
		board.handleInputLetter('a');
		board.handleInputLetter('a');
	}
	
	// Board.reset
	@Test
	public void test25() {
		Board board = Board.getInstance();
		board.reset("res/Button/Rule.png", 0, 0, 0, 0);
	}
	
	// Board.isCorrectAnswer
	@Test
	public void test26() {
		Board board = Board.getInstance();
		boolean res = board.isCorrectAnswer();
		assertEquals(true, res);
	}
	
	@Test
	public void test27() {
		GREGame g=new GREGame();
		g.setBookType(EnumVocabularyBook.GRE);
		g.mouse.isClicked=false;
		Scene s = new PlayingScene();
		Map map = Map.getInstance();
		map.initialize(5, 5, 100, 100, 50, 50, "res/words/test_alg.txt");
		g.loadScene(s);
		s.update();
	}
	@Test
	public void test28() {
		Map.getInstance().initialize(5, 5, 100, 100, 50, 50, "res/words/test_alg.txx");
	}
	@Test
	public void test29() {
		Map.getInstance().initialize(5, 5, 100, 100, 50, 50, "res/words/test_read.txt");
	}
	//playingscene.handleKeyboardInput
	@Test
	public void test30() {
		GREGame g=new GREGame();
		g.setBookType(EnumVocabularyBook.GRE);
		g.key.queuingEvent.add(KeyEvent.VK_ENTER);
		Map map = Map.getInstance();
		map.initialize(5, 5, 100, 100, 50, 50, "res/words/test_alg.txt");
		MapNode[][] map2d = map.getMap();
		Character cxk = Character.getInstance();
		cxk.enter(map2d[2][2]);
		MapNode.setViewNodeNull();
		Scene s = new PlayingScene();
		g.loadScene(s);
		s.update();
	}
	@Test
	public void test31() {
		GREGame g=new GREGame();
		g.setBookType(EnumVocabularyBook.GRE);
		g.key.queuingEvent=null;
		Map map = Map.getInstance();
		map.initialize(5, 5, 100, 100, 50, 50, "res/words/test_alg.txt");
		MapNode[][] map2d = map.getMap();
		Character cxk = Character.getInstance();
		cxk.enter(map2d[2][2]);		
		MapNode.setViewNodeNull();
		Scene s = new PlayingScene();
		g.loadScene(s);
		s.update();
	}
	@Test
	public void test32() {
		GREGame g=new GREGame();
		g.setBookType(EnumVocabularyBook.GRE);
		Map map = Map.getInstance();
		map.initialize(5, 5, 100, 100, 50, 50, "res/words/test_alg.txt");
		MapNode[][] map2d = map.getMap();
		map2d[0][3].handleClickEvent(74, -70);
		g.key.queuingEvent.add(KeyEvent.VK_BACK_SPACE);
		Scene s = new PlayingScene();
		g.loadScene(s);
		s.update();
	}
	@Test
	public void test33() {
		GREGame g=new GREGame();
		g.setBookType(EnumVocabularyBook.GRE);
		Map map = Map.getInstance();
		map.initialize(5, 5, 100, 100, 50, 50, "res/words/test_alg.txt");
		MapNode[][] map2d = map.getMap();
		map2d[0][3].handleClickEvent(74, -70);
		g.key.queuingEvent.add(KeyEvent.VK_A);
		Scene s = new PlayingScene();
		g.loadScene(s);
		s.update();
	}
	@Test
	public void test34() {
		GREGame g=new GREGame();
		g.setBookType(EnumVocabularyBook.GRE);
		Map map = Map.getInstance();
		map.initialize(5, 5, 100, 100, 50, 50, "res/words/test_alg.txt");
		MapNode[][] map2d = map.getMap();
		map2d[0][3].handleClickEvent(74, -70);
		g.key.queuingEvent.add(1+KeyEvent.VK_Z);
		Scene s = new PlayingScene();
		g.loadScene(s);
		s.update();
	}
	@Test
	public void test35() {
		GREGame g=new GREGame();
		g.setBookType(EnumVocabularyBook.GRE);
		Map map = Map.getInstance();
		map.initialize(5, 5, 100, 100, 50, 50, "res/words/test_alg.txt");
		MapNode[][] map2d = map.getMap();
		map2d[0][3].handleClickEvent(74, -70);
		g.key.queuingEvent.add(KeyEvent.VK_A-1);
		Scene s = new PlayingScene();
		g.loadScene(s);
		s.update();
	}
	@Test
	public void test36() {
		GREGame g=new GREGame();
		g.setBookType(EnumVocabularyBook.GRE);
		Map map = Map.getInstance();
		map.initialize(5, 5, 100, 100, 50, 50, "res/words/test_alg.txt");
		MapNode[][] map2d = map.getMap();
		map2d[0][3].handleClickEvent(74, -70);
		g.key.queuingEvent.add(KeyEvent.VK_ENTER);
		Scene s = new PlayingScene();
		g.loadScene(s);
		s.update();
	}
	@Test
	public void test37() {
		GREGame g=new GREGame();
		g.setBookType(EnumVocabularyBook.GRE);
		Map map = Map.getInstance();
		map.initialize(5, 5, 100, 100, 50, 50, "res/words/test_alg.txt");
		MapNode[][] map2d = map.getMap();
		map2d[0][3].handleClickEvent(74, -70);
		g.key.queuingEvent.add(KeyEvent.VK_ENTER);
		Board.getInstance().setWordInfo(new WordInfo("a","a"));
		Board.getInstance().handleInputLetter('a');
		Scene s = new PlayingScene();
		g.loadScene(s);
		s.update();
	}
	// MusicController.updateBackgroundMusic(mousePosition)
	@Test
	public void test38() {
		MusicController mc = MusicController.getInstance();
		Vector2d mouse = new Vector2d(11, GameSettingConstants.APP_HEIGHT/13 + 1);
		mc.handleClickEvent(mouse);
		mc.startBackground();
		mc.handleClickEvent(mouse);
		mc.startBackground();
	}
	
	// Music.Music catch clause
	@Test
	public void test39() {
		Music good = new Music("fakepath", 0);
	}
	
	// PlayingScene.update()
	@Test
	public void test40() {
		GREGame g=new GREGame();
		g.setBookType(EnumVocabularyBook.GRE);
		MapNode mn = new MapNode(0, 0, 0, 0, 0, new WordInfo("",""));
		Scene s = new PlayingScene();
		g.loadScene(s);
		Character c = Character.getInstance();
		mn.addAdj(Map.getInstance().getDummy());
		c.enter(mn);
		s.update();
		assertEquals(c.isEscape(), true);
	}
	
	@Test
	public void test41() {
		MapNode mn = new MapNode(0, 0, 2, 2, 2, new WordInfo("",""));
		mn.getNodeInformation().blocked = true;
		mn.handleClickEvent(1, 1);
		boolean res = MapNode.isViewExist();
		assertEquals(false, res);
	}
}



