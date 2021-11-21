package junitTest;

import static org.junit.Assert.assertEquals;

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
import util.GameTimer;
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
	
	// Dio.isActive
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
	
	// GameResult.increaseCorrectCount
	@Test
	public void test09() {
		GameResult gr = new GameResult();
		gr.increaseCorrectCount();
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
		boolean res = true;
		for(int i=0; i < 4; i++) {
			if (!EnumVocabularyBook.getWordTypePath(EnumVocabularyBook.IntToWordType(i)).equals(arr[i])) {
				res = false;
			}
		}
		if (EnumVocabularyBook.IntToWordType(5) != null) 
			res = false;
		assertEquals(true, res);
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
	}
}
