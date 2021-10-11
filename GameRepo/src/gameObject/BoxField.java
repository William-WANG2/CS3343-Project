package gameObject;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import testCase.TestDrawingScene;
import util.ResourceLoader;

public class BoxField {
	
	private int x,y;
	private int height,width;
	private String message = "";
	private static BufferedImage box = null;
	public BoxField(int x, int y, int h, int w) {
		this.x=x;
		this.y=y;
		this.height=h;
		this.width=w;
	}
	
	public void enter() {
		InputStream stream = ResourceLoader.load(TestDrawingScene.class, "res/box.png", "/box.png" );
		try {
			box = ImageIO.read(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//hover over the MapNode, show the MessageBox containing definition
	public void update(String m) {
		this.message=m;
		
	}
	//
	public void render(Graphics2D g) {
		g.drawImage(box, x, y, height, width, null);
		g.drawString(message, x+10, y+10);
	}

}
