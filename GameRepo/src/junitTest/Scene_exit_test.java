package junitTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import game.GREGame;
import gameObject.EnumVocabularyBook;
import gameObject.GameResult;
import scenes.LoginScene;
import scenes.PlayingScene;
import scenes.Scene;
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
	public void test04() {
		GREGame g=new GREGame();
		g.mouse.isClicked=true;
		g.mouse.mousePos = new Vector2d(477, 285);//click rule
		Scene s = new LoginScene();
		g.loadScene(s);
		s.update();
		//assertEquals(true, );
	}
	@Test
	public void test05() {
		GREGame g=new GREGame();
		g.mouse.isClicked=true;
		g.mouse.mousePos = new Vector2d(477, 585);//click button
		Scene s = new LoginScene();
		g.loadScene(s);
		s.update();
		//assertEquals();
	}
	@Test
	public void test06() {
		GREGame g=new GREGame();
		g.mouse.isClicked=false;
		g.mouse.mousePos = new Vector2d(477, 585);//false
		Scene s = new LoginScene();
		g.loadScene(s);
		s.update();
		//assertEquals();
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
		//assertEquals();
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
	}
	//PlayingScene.exit
	/*@Test
	public void test09() {
		GREGame g=new GREGame();
		Scene s = new PlayingScene();
		g.loadScene(s);
		s.update();
		s.exit();
	}*/
	//LoginScene.exit
	//ResultScene.exit
}
