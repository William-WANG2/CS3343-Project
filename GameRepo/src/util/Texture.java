package util;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/*Texture is a wrapped image*/
public class Texture {
	
	private BufferedImage image;
	private Transform transform;
	private int height;
	private int width;
	
	public Texture(BufferedImage image, Transform transform) {
		this.image = image;
		this.transform = transform;
	}
	
	public BufferedImage getImage() {
		return this.image;
	}
	
	public float getScaleX() {
		return this.transform.scale.x;
	}
	
	public float getScaleY() {
		return this.transform.scale.y;
	}
	
	public float getPosX() {
		return this.transform.position.x;
	}
	
	public float getPosY() {
		return this.transform.position.y;
	}
	
	public float getHeight() {
		height = (int)(image.getHeight() * transform.scale.y);
		return height;
	}
	
	static public Texture loadImage(String filePath, int x, int y, int width, int height) {
		
		InputStream stream = ResourceLoader.load(Texture.class, filePath);
		BufferedImage image = null;
		try {
			
			image = ImageIO.read(stream);
		} catch (IOException |IllegalArgumentException  e) {
			
			return null;
		}
		
		Vector2f position = new Vector2f(x, y);
		Vector2f scale = new Vector2f((float)width/(float)image.getWidth(), (float)height/(float)image.getHeight());
		Transform transform = new Transform(position, scale);
		return new Texture(image, transform);
	}
	
	public void render(Graphics2D g) {
		AffineTransform transform = new AffineTransform(this.getScaleX(), 0.0, 0.0, this.getScaleY(), this.getPosX(), this.getPosY());
		g.drawImage(this.getImage(), transform, null);
	}
}
