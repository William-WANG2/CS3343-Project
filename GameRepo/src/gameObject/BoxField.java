package gameObject;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import util.Texture;

public class BoxField {
	
	private int x,y;
	private int height,width;
	private String message = "";
	private static Texture box = null;
	public BoxField(int x, int y, int w, int h) {
		this.x=x;
		this.y=y;
		this.height=h;
		this.width=w;
	}
	
	public void enter() {
		if(box == null) {
			String path = "res/box.png";
			box = Texture.loadImage(path, x, y, width, height);
		}
	}
	//hover over the MapNode, show the MessageBox containing definition
	public void update(String m) {
		this.message=m;
		
	}
	//
	public void render(Graphics2D g) {
		AffineTransform transform = new AffineTransform(box.getScaleX(), 0.0, 0.0, box.getScaleY(), box.getPosX(), box.getPosY());
		g.drawImage(box.getImage(), transform, null);
		g.drawString(message, x+30, y+30);
	}
}
