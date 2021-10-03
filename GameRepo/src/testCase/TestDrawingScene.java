package testCase;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import scenes.Scene;
import util.*;

public class TestDrawingScene extends Scene{

	Texture testTexture;
	
	@Override
	public void enter() {
		// TODO Auto-generated method stub
		InputStream stream = ResourceLoader.load(TestDrawingScene.class, "res/textures/bricks.jpg", "textures/bricks.jpg" );
	
		BufferedImage image = null;
		try {
			image = ImageIO.read(stream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		Vector2f position = new Vector2f(GlobalConstants.WORLD_WIDTH / 2.0f, GlobalConstants.WORLD_HEIGHT / 2.0f);
		Transform transform = new Transform(position, new Vector2f(1.0f, 1.0f));
		Texture texture = new Texture(image, transform);
		testTexture = texture;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		//Texture.render();
		//研究一下为什么需要视口变换，看一下是怎么画上去的
		AffineTransform transform = AffineTransform.getTranslateInstance(400, 300);
		g.drawImage(testTexture.getImage(), transform, null);
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

}
