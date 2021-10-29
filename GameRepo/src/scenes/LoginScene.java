package scenes;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import testCase.TestClickingButton;
import testCase.TestDrawingScene;
import util.*;

public class LoginScene extends Scene {

	Button StartButton;
	
	Mouse mouse;
	Texture texture;
	ArrayList<Button> buttons = new ArrayList<Button>();
	@Override
	public void enter() {
		// add pic
		InputStream stream = ResourceLoader.load(TestDrawingScene.class, "res/textures/bricks.jpg", "/textures/bricks.jpg" );
		BufferedImage image = null;
		try {
			image = ImageIO.read(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// set pic params
		Vector2f position = new Vector2f(GlobalConstants.WORLD_WIDTH / 2.0f, GlobalConstants.WORLD_HEIGHT / 2.0f);
		Vector2f scale = new Vector2f(1.0f, 1.0f);
		Transform transform = new Transform(position, scale);
		Texture texture = new Texture(image, transform);
		this.texture = texture;
		
		// add button
		Button btStart = new Button();
		buttons.add(btStart);
		
		mouse = mApp.mouse;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics2D g) {
		AffineTransform transform = AffineTransform.getTranslateInstance(400, 300);
		g.drawImage(texture.getImage(), transform, null);
		for (Button e: buttons) {
			e.render(g);
		}
	}

	@Override
	public void exit() {
	}
}
