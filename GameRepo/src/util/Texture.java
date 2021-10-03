package util;

import java.awt.image.BufferedImage;

public class Texture {
	
	private BufferedImage image;
	private Transform transform;
	
	public Texture(BufferedImage image, Transform transform) {
		this.image = image;
		this.transform = transform;
	}
	
	public BufferedImage getImage() {
		return this.image;
	}
}
