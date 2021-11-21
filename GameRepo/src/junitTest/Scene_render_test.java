package junitTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gameObject.Board;
import gameObject.Character;
import gameObject.Map;
import gameObject.MapNode;
import gameObject.WordInfo;
import util.ResourceLoader;
import util.Texture;
import util.Transform;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Canvas;
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
				image = ImageIO.read(ResourceLoader.load(Texture.class, "res/animation/caixukun1.jpg", null));
			} catch (IOException e) {
				e.printStackTrace();
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
				image = ImageIO.read(ResourceLoader.load(Texture.class, "res/animation/caixukun1.jpg", null));
			} catch (IOException e) {
				e.printStackTrace();
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
				image = ImageIO.read(ResourceLoader.load(Texture.class, "res/animation/caixukun1.jpg", null));
			} catch (IOException e) {
				e.printStackTrace();
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
				image = ImageIO.read(ResourceLoader.load(Texture.class, "res/animation/caixukun1.jpg", null));
			} catch (IOException e) {
				e.printStackTrace();
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
		
		// 

}
