package testCase;
import util.*;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import scenes.Scene;

/*
 * by zyy
 */
public class TestClickingButton extends Scene {
	
	Texture testTexture;
	BoundingBox btnRegion;
	Boolean isClicked = false;
	Vector2f mousePos = new Vector2f(-1.0f, -1.0f);
	
	@Override
	public void enter() {
		
		InputStream stream = ResourceLoader.load(TestDrawingScene.class, "res/textures/bricks.gif", "/textures/bricks.gif" );
		BufferedImage image = null;
		try {
			image = ImageIO.read(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Vector2f position = new Vector2f(GlobalConstants.APP_WIDTH / 2.0f, GlobalConstants.APP_HEIGHT / 2.0f);
		Vector2f scale = new Vector2f(1.0f, 1.0f);
		Transform transform = new Transform(position, scale);
		Texture texture = new Texture(image, transform);
		testTexture = texture;
		
		btnRegion = new BoundingBox(100, 100, 400, 400);
		
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

		
}
	
	
