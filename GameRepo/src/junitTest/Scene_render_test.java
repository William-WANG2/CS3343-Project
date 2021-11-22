package junitTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import game.GREGame;
import gameObject.Board;
import gameObject.Character;
import gameObject.EnumVocabularyBook;
import gameObject.Map;
import gameObject.MapNode;
import gameObject.WordInfo;
import gameObject.GameButton;
import gameObject.GameResult;
import util.ResourceLoader;
import util.Texture;
import util.Transform;
import util.Mouse;
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
		public void test01() {
			BufferedImage image = null;
			try {
				image = ImageIO.read(ResourceLoader.load(Texture.class, "res/animation/caixukun1.jpg"));
			} catch (IOException e) {
				
			}
			Transform transform = new Transform();
			Texture texture = new Texture(image, transform);
			BufferedImage res = texture.getImage();
			assertEquals(image, res);
		}
		
		// Texture.getScaleY
		@Test
		public void test02() {
			BufferedImage image = null;
			try {
				image = ImageIO.read(ResourceLoader.load(Texture.class, "res/animation/caixukun1.jpg"));
			} catch (IOException e) {
				
			}
			Transform transform = new Transform();
			Texture texture = new Texture(image, transform);
			float res = texture.getScaleY();
			assertEquals(0, 0.0f, res);
		}
		
		// Texture.getScaleY
		@Test
		public void test03() {
			BufferedImage image = null;
			try {
				image = ImageIO.read(ResourceLoader.load(Texture.class, "res/animation/caixukun1.jpg"));
			} catch (IOException e) {
				
			}
			Transform transform = new Transform();
			Texture texture = new Texture(image, transform);
			float res = texture.getScaleX();
			assertEquals(0, 0.0f, res);
		}
		
		// Character.getNode
		@Test
		public void test04(){
			Character character = Character.getInstance();
			MapNode node = new MapNode(0, 0, 0, 0, 0, null);
			character.enter(node);
			MapNode res = character.getNode();
			assertEquals(node, res);
		}
		
		// Texture.render
		@Test
		public void test05() {		
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
			try {
				image = ImageIO.read(ResourceLoader.load(Texture.class, "res/animation/caixukun1.jpg"));
			} catch (IOException e) {
				
			}
			Transform transform = new Transform();
			Texture texture = new Texture(image, transform);			
			texture.render((Graphics2D)g);
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
		public void test09() {
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
			board.reset("res/animation/caixukun1.jpg", 0, 0, 0, 0);
			board.render((Graphics2D)g);
		}
		
		// GameButton.render
		@Test
		public void test11() {
			GameButton gameButton = new GameButton("res/animation/caixukun1.jpg", "res/animation/caixukun2.jpg",0,0,0,0);
			JFrame f = new JFrame();
			Canvas canvas = new Canvas();
			f.getContentPane().add(canvas);
			f.setSize(100, 100);
			f.setTitle("Ji ni tai mei");
			f.setVisible(true);
			canvas.createBufferStrategy(2);
			BufferStrategy bs = canvas.getBufferStrategy();
			Graphics g = bs.getDrawGraphics();
			gameButton.render((Graphics2D)g);
		}
		
		// RuleScene.render
		@Test
		public void test17() {
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
			ruleScene.render((Graphics2D)g);
		}
		
		// Scene.render
		@Test
		public void test18() {
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
			scene.render((Graphics2D)g);
		}
		
		//ResultScene.render
		@Test
		public void test19() {
			ResultScene ruleScene = new ResultScene();
			GREGame gre = new GREGame();
			gre.loadScene(ruleScene);
			
			GameResult r=new GameResult();
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
			ruleScene.render((Graphics2D)g);

		}
		
		@Test
		public void test20() {
			ResultScene ruleScene = new ResultScene();
			GREGame gre = new GREGame();
			gre.loadScene(ruleScene);
			
			GameResult r=new GameResult();
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
			ruleScene.render((Graphics2D)g);

		}
		
		//ResultScene.update
		@Test
		public void test21() {
			ResultScene ruleScene = new ResultScene();
			GREGame gre = new GREGame();
			gre.loadScene(ruleScene);
			
			GameResult r=new GameResult();
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
		
		//PlayingScene.handleMouseClick()
		@Test
		public void test22() {
			PlayingScene r=new PlayingScene();
			GREGame g=new GREGame();
			g.setBookType(EnumVocabularyBook.IntToWordType(0));
			g.loadScene(r);
			g.mouse.isClicked=true;
			r.enter();
			r.update();
			r.getNextScene();

		}
		
		@Test
		public void test23() {
			LoginScene r=new LoginScene();
			GREGame x=new GREGame();
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
			r.render((Graphics2D)g);

		}
		
		

		
		


}
