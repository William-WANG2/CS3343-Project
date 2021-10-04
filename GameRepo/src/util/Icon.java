package util;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Icon {
	private int x, y, width, height;
	private Image image;
	
	public Icon(String path) {
		this.image = new ImageIcon(path).getImage();
		// default size
		this.width = 100;
		this.height = 100;
	}
	
	// Overload the constructor to set flexible icon
	public Icon(String fileName,
			int x, int y,
			int width, int height) {
		this.image = new ImageIcon(fileName).getImage();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public Image getImage() {
		return image;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
