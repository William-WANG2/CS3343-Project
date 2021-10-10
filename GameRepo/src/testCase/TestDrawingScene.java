package testCase;
import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import scenes.Scene;
import util.*;

public class TestDrawingScene extends Scene{

	Boolean mouseClicked = false;
	Vector2f mousePosition;
	Texture testTexture;
	
	@Override
	public void enter() {
		
		InputStream stream = ResourceLoader.load(TestDrawingScene.class, "res/textures/brick.jpg", "/textures/brick.jpg" );
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
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics2D g) {
		AffineTransform transform = AffineTransform.getTranslateInstance(400, 300);
		g.drawImage(testTexture.getImage(), transform, null);
	}

	@Override
	public void exit() {
	}

	@Override
	public void setupInput(Canvas canvas) {
		// TODO Auto-generated method stub
		
	}

}
