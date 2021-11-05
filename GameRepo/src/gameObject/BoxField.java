package gameObject;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import util.Texture;

public class BoxField {
	
	private int x,y;
	private int height,width;
	private String message = "";
	private static Texture boxTexture = null;
	public BoxField(int x, int y, int w, int h) {
		this.x=x;
		this.y=y;
		this.height=h;
		this.width=w;
	}
	
	public void enter() {
		if(boxTexture == null) {
			String path = "res/textures/box.png";
			boxTexture = Texture.loadImage(path, x, y, width, height);
		}
	}
	//hover over the MapNode, show the MessageBox containing definition
	public void update(String m) {
		this.message=m;
		
	}
	//
	public void render(Graphics2D g) {
		AffineTransform transform = new AffineTransform(boxTexture.getScaleX(), 0.0, 0.0, boxTexture.getScaleY(), boxTexture.getPosX(), boxTexture.getPosY());
		g.drawImage(boxTexture.getImage(), transform, null);
		g.drawString(message, x+30, y+30);
	}
}
