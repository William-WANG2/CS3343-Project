package testCase;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import gameObject.BoxController;
import gameObject.Dio;
import gameObject.Map;
import scenes.Scene;
import util.*;

public class TestDrawingScene extends Scene{

	Mouse mouse;
	Key key;
	Texture testTexture;
	BufferedImage x;
	Map m;
	BoxController box;
	Dio dio;
	@Override
	public void enter() {
		
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
		
		m = Map.getInstance();
		m.initialize(10, 10, 200, 200, "./res/word.txt"); 
		m.enter();
		box = BoxController.getInstance();
		box.enter();
		dio = Dio.getInstance();
		dio.initialize(m.getMap()[4][5]);
		dio.enter();
		mouse = mApp.mouse;
		key = mApp.key;
	}

	@Override
	public void update() {


		m.update(mouse, key);

	}

	@Override
	public void render(Graphics2D g) {
		AffineTransform transform = AffineTransform.getTranslateInstance(400, 300);
		g.drawImage(testTexture.getImage(), transform, null);
		m.render(g);
		box.render(g);
		dio.render(g);
	}

	@Override
	public void exit() {
	}


}
