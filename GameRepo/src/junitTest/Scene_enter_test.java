package junitTest;

import static org.junit.Assert.assertEquals;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

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
import scenes.LoginScene;
import scenes.PlayingScene;
import scenes.ResultScene;
import scenes.RuleScene;
import scenes.Scene;
import util.Music;
import util.ResourceLoader;
import util.Texture;
import util.Transform;

public class Scene_enter_test {
	//Unit test begin
	//Texture.loadImage
	@Test
	public void test01() {
		Texture.loadImage("res/sprite/kunkun1.pn", 0, 0, 0, 0);
	} 
	@Test
	public void test02() {
		Texture.loadImage("res/sprite/kunkun1.png", 0, 0, 0, 0);

	}
	
	//Music.loop
	@Test
	public void test03() {
		String filepath = "res/music/bad.wav";
		Music t = new Music(filepath, 0);
		t.loop();
		
	}
	
	//GameButton
	@Test
	public void test04() {
		GameButton b=new GameButton("res/music/musicOn.png", "res/music/musicOff.png", 10, GameSettingConstants.APP_HEIGHT/13, 50, 50);	
	}
	
	//GREGame.getGameResult()
	@Test
	public void test05() {
		GREGame g=new GREGame();
		GameResult result=g.getGameResult();
		assertEquals(result,null);
	}
	
	//Board.reset()
	@Test
	public void test06() {
		 Board.getInstance().reset("res/textures/box.png", GameSettingConstants.APP_WIDTH/2 - 20, 100, (int)(GameSettingConstants.APP_WIDTH), 400);
	}
	//Unit test end
	
	//Integration Testing begin
	//MapNode.enter()
	@Test
	public void test07() {
		MapNode m=new MapNode(0, 0, 0, 0, 0, null);
		m.enter();
	}
	
	
	
	//Character.enter()
	@Test
	public void test08() {
	
		int cntWord=0;
		MapNode m=new MapNode(cntWord, cntWord, cntWord, cntWord, cntWord, null);
		Character.getInstance().enter(m);
		
	}
	
	//MusicController.startBackground()
	@Test
	public void test09() {
		MusicController.getInstance().startBackground();
	}
	
	//RuleScene.enter()
	@Test
	public void test10() {
		RuleScene r=new RuleScene();
		GREGame g=new GREGame();
		g.loadScene(r);
		r.enter();
	}
	
	//ResultScene.enter()
		@Test
		public void test11() {
			ResultScene r=new ResultScene();
			GREGame g=new GREGame();
			g.loadScene(r);
			r.enter();
		}
	
	//Map.initialize()
		@Test
		public void test12() {
			Map.getInstance().initialize(5, 5, 0, 0, 0, 0, "res/words/PRIMARY.xml");
		}
		
	//LoginScene.enter()
		@Test	
		public void test13() {
			LoginScene r=new LoginScene();
			GREGame g=new GREGame();
			g.loadScene(r);
			r.enter();
		}
		
	//PlayingScene.enter()
		@Test	
		public void test14() {
			PlayingScene r=new PlayingScene();
			GREGame g=new GREGame();
			g.setBookType(EnumVocabularyBook.IntToWordType(0));
			g.loadScene(r);
			r.enter();
		}
		@Test
		public void test15() {
			PlayingScene r=new PlayingScene();
			GREGame g=new GREGame();
			g.setBookType(EnumVocabularyBook.IntToWordType(1));
			g.loadScene(r);
			r.enter();
		}
		@Test
		public void test16() {
			PlayingScene r=new PlayingScene();
			GREGame g=new GREGame();
			g.setBookType(EnumVocabularyBook.IntToWordType(2));
			g.loadScene(r);
			r.enter();
		}
		@Test
		public void test17() {
			PlayingScene r=new PlayingScene();
			GREGame g=new GREGame();
			g.setBookType(EnumVocabularyBook.IntToWordType(3));
			g.loadScene(r);
			r.enter();
		}
		
		//Scene.enter()
		@Test
		public void test18() {
			Scene r=new PlayingScene();
			r.setNextScene(false);
			GREGame g=new GREGame();
			g.setBookType(EnumVocabularyBook.IntToWordType(3));
			g.loadScene(r);
			r.enter();
		}
		
		//MapNode.setViewNodeNull()
		@Test
		public void test19() {
			MapNode m=new MapNode(0, 0, 0, 0, 0, null);
			m.setViewNodeNull();
		}
		
		
		//MapNode.Render()
		@Test
		public void test20() {
			JFrame f = new JFrame();
			Canvas canvas = new Canvas();
			f.getContentPane().add(canvas);
			f.setSize(100, 100);
			f.setTitle("Ji ni tai mei");
			f.setVisible(true);
			canvas.createBufferStrategy(2);
			BufferStrategy bs = canvas.getBufferStrategy();
			Graphics g = bs.getDrawGraphics();
			MapNode m=new MapNode(5, 5, 0, 0, 0, null);
			m.getNodeInformation().blocked=true;
			m.render((Graphics2D)g);
			}
		@Test
		public void test21() {
			JFrame f = new JFrame();
			Canvas canvas = new Canvas();
			f.getContentPane().add(canvas);
			f.setSize(100, 100);
			f.setTitle("Ji ni tai mei");
			f.setVisible(true);
			canvas.createBufferStrategy(2);
			BufferStrategy bs = canvas.getBufferStrategy();
			Graphics g = bs.getDrawGraphics();
			MapNode m=new MapNode(5, 5, 0, 0, 0, null);
			m.getNodeInformation().blocked=false;
			m.render((Graphics2D)g);
			}
		
		@Test
		public void test22() {
			JFrame f = new JFrame();
			Canvas canvas = new Canvas();
			f.getContentPane().add(canvas);
			f.setSize(100, 100);
			f.setTitle("Ji ni tai mei");
			f.setVisible(true);
			canvas.createBufferStrategy(2);
			BufferStrategy bs = canvas.getBufferStrategy();
			Graphics g = bs.getDrawGraphics();
			MapNode m=new MapNode(5, 5, 5, 0, 0, null);
			m.getNodeInformation().blocked=false;
			m.handleClickEvent(10, 10);
			m.render((Graphics2D)g);
			}
		}
	 