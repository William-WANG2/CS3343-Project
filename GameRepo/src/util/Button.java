package util;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import scenes.Scene;



public class Button extends Scene {
	Texture testTexture;
	BoundingBox btnRegion;
	Boolean isClicked = false;
	Vector2f mousePos = new Vector2f(-1.0f, -1.0f);
	
	private Transform transform;
	private Texture texture;
	
	// default button constructor
	public Button() {
		InputStream stream = ResourceLoader.load(Button.class, "res/textures/bricks.jpg", "/textures/bricks.jpg" );
		BufferedImage image = null;
		try {
			image = ImageIO.read(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Vector2f position = new Vector2f(GlobalConstants.WORLD_WIDTH / 2.0f, GlobalConstants.WORLD_HEIGHT / 2.0f);
		Vector2f scale = new Vector2f(1.0f, 1.0f);
		transform = new Transform(position, scale);
		texture = new Texture(image, transform);
		btnRegion = new BoundingBox(100, 100, 400, 400);
	}
	
	@Override
	public void enter() {
	}

	@Override
	public void update() {
		if(isClicked && btnRegion.isInGeo(mousePos))
			System.out.print("Proceed to next scene");
		isClicked = false;
	}

	@Override
	public void render(Graphics2D g) {
		AffineTransform transform = AffineTransform.getTranslateInstance(100, 100);
		g.drawImage(testTexture.getImage(), transform, null);
	}

	@Override
	public void exit() {
		
	}

	public Transform getTransform() {
		return transform;
	}
}
