package junitTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fileReader.TxtReader;
import fileReader.XMLReader;
import game.GREGame;
import game.GameSettingConstants;
import gameObject.Board;
import gameObject.Character;
import gameObject.EnumVocabularyBook;
import gameObject.Map;
import gameObject.MapNode;
import gameObject.MusicController;
import gameObject.WordInfo;
import gameObject.GameButton;
import gameObject.GameResult;
import util.ResourceLoader;
import util.Texture;
import util.Transform;
import util.Vector2d;
import util.Vector2f;
import util.BoundingBox;
import util.Mouse;
import util.Music;
import scenes.LoginScene;
import scenes.PlayingScene;
import scenes.ResultScene;
import scenes.RuleScene;
import scenes.Scene;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.image.BufferStrategy;

public class Scene_render_test {
	// Unit testing begin
	// Texture.getImage
	@Test
	public void test01() throws IOException {
		BufferedImage image = null;
		image = ImageIO.read(ResourceLoader.load(Texture.class, "res/animation/caixukun1.jpg"));
		Transform transform = new Transform();
		Texture texture = new Texture(image, transform);
		BufferedImage res = texture.getImage();
		assertEquals(image, res);
	}

	// Texture.getScaleY
	@Test
	public void test02() throws IOException {
		BufferedImage image = null;
		image = ImageIO.read(ResourceLoader.load(Texture.class, "res/animation/caixukun1.jpg"));
		Transform transform = new Transform();
		Texture texture = new Texture(image, transform);
		float res = texture.getScaleY();
		assertEquals(0, 0.0f, res);
	}

	// Texture.getScaleX
	@Test
	public void test03() throws IOException {
		BufferedImage image = null;
		image = ImageIO.read(ResourceLoader.load(Texture.class, "res/animation/caixukun1.jpg"));
		Transform transform = new Transform();
		Texture texture = new Texture(image, transform);
		float res = texture.getScaleX();
		assertEquals(0, 0.0f, res);
	}

	// Character.getNode
	@Test
	public void test04() {
		Character character = Character.getInstance();
		MapNode node = new MapNode(0, 0, 0, 0, 0, null);
		character.enter(node);
		MapNode res = character.getNode();
		assertEquals(node, res);
	}

	// Texture.render
	@Test
	public void test05() throws IOException {
		JFrame f = new JFrame();
		Canvas canvas = new Canvas();
		f.getContentPane().add(canvas);
		f.setSize(100, 100);
		f.setTitle("Ji ni tai mei");
		f.setVisible(true);
		canvas.createBufferStrategy(2);
		BufferStrategy bs = canvas.getBufferStrategy();
		Graphics g = bs.getDrawGraphics();

		BufferedImage image = null;
		image = ImageIO.read(ResourceLoader.load(Texture.class, "res/animation/caixukun1.jpg"));
		Transform transform = new Transform();
		Texture texture = new Texture(image, transform);
		texture.render((Graphics2D) g);
	}

	// WordInfo.getDefinition
	@Test
	public void test06() {
		WordInfo wordInfo = new WordInfo("abcd", "efgh");
		String res = wordInfo.getDefinition();
		assertEquals("abcd", res);

	}

	// WordInfo.getWord
	@Test
	public void test07() {
		WordInfo wordInfo = new WordInfo("abcd", "efgh");
		String res = wordInfo.getWord();
		assertEquals("efgh", res);
	}

	// Integration test begin
	// Board.render
	@Test
	public void test08() {
		JFrame f = new JFrame();
		Canvas canvas = new Canvas();
		f.getContentPane().add(canvas);
		f.setSize(100, 100);
		f.setTitle("Ji ni tai mei");
		f.setVisible(true);
		canvas.createBufferStrategy(2);
		BufferStrategy bs = canvas.getBufferStrategy();
		Graphics g = bs.getDrawGraphics();

		Board board = Board.getInstance();
		WordInfo wordInformation = new WordInfo("abcd", "efgh");
		board.setWordInfo(wordInformation);
		board.handleInputLetter('a');
		board.reset("res/animation/caixukun1.jpg", 0, 0, 0, 0);
		board.render((Graphics2D) g);
	}

	@Test
	public void test9() {
		JFrame f = new JFrame();
		Canvas canvas = new Canvas();
		f.getContentPane().add(canvas);
		f.setSize(100, 100);
		f.setTitle("Ji ni tai mei");
		f.setVisible(true);
		canvas.createBufferStrategy(2);
		BufferStrategy bs = canvas.getBufferStrategy();
		Graphics g = bs.getDrawGraphics();

		Board board = Board.getInstance();
		board.setWordInfo(null);
		board.reset("res/animation/caixukun3.jpg", 0, 0, 0, 0);
		board.render((Graphics2D) g);
	}

	// GameButton.render
	@Test
	public void test10() {
		GameButton gameButton = new GameButton("res/animation/caixukun1.jpg", "res/animation/caixukun2.jpg", 0, 0, 0,
				0);
		JFrame f = new JFrame();
		Canvas canvas = new Canvas();
		f.getContentPane().add(canvas);
		f.setSize(100, 100);
		f.setTitle("Ji ni tai mei");
		f.setVisible(true);
		canvas.createBufferStrategy(2);
		BufferStrategy bs = canvas.getBufferStrategy();
		Graphics g = bs.getDrawGraphics();
		gameButton.render((Graphics2D) g);
	}

	// RuleScene.render
	@Test
	public void test11() {
		RuleScene ruleScene = new RuleScene();
		GREGame gre = new GREGame();
		gre.loadScene(ruleScene);
		ruleScene.enter();
		JFrame f = new JFrame();
		Canvas canvas = new Canvas();
		f.getContentPane().add(canvas);
		f.setSize(100, 100);
		f.setTitle("Ji ni tai mei");
		f.setVisible(true);
		canvas.createBufferStrategy(2);
		BufferStrategy bs = canvas.getBufferStrategy();
		Graphics g = bs.getDrawGraphics();
		ruleScene.render((Graphics2D) g);
	}

	// Scene.render
	@Test
	public void test12() {
		Scene scene = new PlayingScene();
		scene.setNextScene(false);
		GREGame gre = new GREGame();
		gre.setBookType(EnumVocabularyBook.IntToWordType(3));
		gre.loadScene(scene);
		JFrame f = new JFrame();
		Canvas canvas = new Canvas();
		f.getContentPane().add(canvas);
		f.setSize(100, 100);
		f.setTitle("Ji ni tai mei");
		f.setVisible(true);
		canvas.createBufferStrategy(2);
		BufferStrategy bs = canvas.getBufferStrategy();
		Graphics g = bs.getDrawGraphics();
		scene.render((Graphics2D) g);
	}

	// ResultScene.render
	@Test
	public void test13() {
		ResultScene ruleScene = new ResultScene();
		GREGame gre = new GREGame();
		gre.loadScene(ruleScene);

		GameResult r = new GameResult();
		r.setIsWin(true);
		gre.setGameResult(r);
		ruleScene.enter();
		JFrame f = new JFrame();
		Canvas canvas = new Canvas();
		f.getContentPane().add(canvas);
		f.setSize(100, 100);
		f.setTitle("Ji ni tai mei");
		f.setVisible(true);
		canvas.createBufferStrategy(2);
		BufferStrategy bs = canvas.getBufferStrategy();
		Graphics g = bs.getDrawGraphics();
		ruleScene.render((Graphics2D) g);

	}

	@Test
	public void test14() {
		ResultScene ruleScene = new ResultScene();
		GREGame gre = new GREGame();
		gre.loadScene(ruleScene);

		GameResult r = new GameResult();
		r.setIsWin(false);
		gre.setGameResult(r);
		ruleScene.enter();
		JFrame f = new JFrame();
		Canvas canvas = new Canvas();
		f.getContentPane().add(canvas);
		f.setSize(100, 100);
		f.setTitle("Ji ni tai mei");
		f.setVisible(true);
		canvas.createBufferStrategy(2);
		BufferStrategy bs = canvas.getBufferStrategy();
		Graphics g = bs.getDrawGraphics();
		ruleScene.render((Graphics2D) g);

	}

	// ResultScene.update
	@Test
	public void test15() {
		ResultScene ruleScene = new ResultScene();
		GREGame gre = new GREGame();
		gre.loadScene(ruleScene);

		GameResult r = new GameResult();
		r.setIsWin(false);
		gre.setGameResult(r);
		ruleScene.enter();
		JFrame f = new JFrame();
		Canvas canvas = new Canvas();
		f.getContentPane().add(canvas);
		f.setSize(100, 100);
		f.setTitle("Ji ni tai mei");
		f.setVisible(true);
		canvas.createBufferStrategy(2);
		BufferStrategy bs = canvas.getBufferStrategy();
		Graphics g = bs.getDrawGraphics();
		ruleScene.update();

	}

	// PlayingScene.handleMouseClick()
	@Test
	public void test16() {
		PlayingScene r = new PlayingScene();
		GREGame g = new GREGame();
		g.setBookType(EnumVocabularyBook.IntToWordType(0));
		g.loadScene(r);
		g.mouse.isClicked = true;
		r.enter();
		r.update();
		r.getNextScene();

	}

	// LoginScene.render
	@Test
	public void test17() {
		LoginScene r = new LoginScene();
		GREGame x = new GREGame();
		x.loadScene(r);
		r.enter();
		JFrame f = new JFrame();
		Canvas canvas = new Canvas();
		f.getContentPane().add(canvas);
		f.setSize(100, 100);
		f.setTitle("Ji ni tai mei");
		f.setVisible(true);
		canvas.createBufferStrategy(2);
		BufferStrategy bs = canvas.getBufferStrategy();
		Graphics g = bs.getDrawGraphics();
		r.render((Graphics2D) g);

	}

	@Test
	public void test18() {
		ResourceLoader r = new ResourceLoader();
	}

	@Test
	public void test19() {
		String filepath = null;
		Music t = new Music(filepath, 0);
	}

	// BoundingBox.isInGeo
	@Test
	public void test20() {
		BoundingBox b = new BoundingBox(0, 0, 0, 0);
		b.isInGeo(new Vector2f(0, 0));
	}

	@Test
	public void test21() {
		GameSettingConstants g = new GameSettingConstants();
	}

	@Test
	public void test22() {
		TxtReader t = new TxtReader();
	}

	@Test
	public void test23() {
		XMLReader t = new XMLReader();
	}

	@Test
	public void test24() {
		EnumVocabularyBook.getWordTypePath(EnumVocabularyBook.IntToWordType(4));
	}

	@Test
	public void test25() {
		MusicController.getInstance().handleClickEvent(new Vector2d(10, GameSettingConstants.APP_HEIGHT / 13));
		MusicController.getInstance().handleClickEvent(new Vector2d(10, GameSettingConstants.APP_HEIGHT / 13));

	}

	@Test
	public void test26() {
		MusicController.getInstance().handleClickEvent(new Vector2d(10, GameSettingConstants.APP_HEIGHT / 13));
		MusicController.getInstance().startBackground();
	}

}
