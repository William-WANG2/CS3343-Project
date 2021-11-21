package junitTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gameObject.Character;
import gameObject.MapNode;
import util.ResourceLoader;
import util.Texture;
import util.Transform;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Canvas;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
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
		public void test05() {
			BufferedImage image = null;
			try {
				image = ImageIO.read(ResourceLoader.load(Texture.class, "res/animation/caixukun1.jpg", null));
			} catch (IOException e) {
				
			}
			Transform transform = new Transform();
			Texture texture = new Texture(image, transform);
			Canvas canvas = new Canvas();
			BufferStrategy bs = canvas.getBufferStrategy();
			Graphics2D g = null;
			texture.render(g);
		}

}
