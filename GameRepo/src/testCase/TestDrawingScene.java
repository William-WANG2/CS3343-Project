package testCase;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import gameObject.Map;
import scenes.Scene;
import util.*;

public class TestDrawingScene extends Scene{

	Mouse mouse;
	Texture testTexture;
	BufferedImage x;
	Map m;
	@Override
	public void enter() {
		
		InputStream streamx = ResourceLoader.load(TestDrawingScene.class, "res/circle/orange.png", "/circle/orange.png" );
		try {
			x = ImageIO.read(streamx);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		InputStream stream = ResourceLoader.load(TestDrawingScene.class, "res/textures/bricks.jpg", "/textures/bricks.jpg" );
		BufferedImage image = null;
		try {
			image = ImageIO.read(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Vector2f position = new Vector2f(GlobalConstants.WORLD_WIDTH / 2.0f, GlobalConstants.WORLD_HEIGHT / 2.0f);
		Vector2f scale = new Vector2f(1.0f, 1.0f);
		Transform transform = new Transform(position, scale);
		Texture texture = new Texture(image, transform);
		testTexture = texture;
		
		m = new Map();
		m.initialize(5, 4, 100, 100, ""); 
		m.enter();
		
		mouse = mApp.mouse;
	}

	@Override
	public void update() {
		m.update(mouse);
	}

	@Override
	public void render(Graphics2D g) {
		AffineTransform transform = AffineTransform.getTranslateInstance(400, 300);
		g.drawImage(testTexture.getImage(), transform, null);
		m.render(g);
	}

	@Override
	public void exit() {
	}


}
