package junitTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gameObject.Board;
import gameObject.Character;
import gameObject.GameButton;
import gameObject.GameResult;
import gameObject.MapNode;
import gameObject.MusicController;
import gameObject.WordInfo;
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
		assertEquals(false, res);
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
}
