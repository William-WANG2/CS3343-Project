package gameObject;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import scenes.Scene;
import util.BoundingBox;
import util.Mouse;
import util.Texture;
import util.Vector2d;

public class Button {
	
	private Texture currTexture;
	private Texture unclickTexture;
	private Texture clickedTexture;
	private BoundingBox btnRegion;
	private Boolean isClicked;

	public Button() {
	}
	
	public Button(String texturePath1, String texturePath2, int left, int top, int width, int height) { //One is origin image, one is clicked image
		
		unclickTexture = Texture.loadImage(texturePath1, left, top, width, height);
		clickedTexture = Texture.loadImage(texturePath2, left, top, width, height);
		currTexture = unclickTexture;
		btnRegion = new BoundingBox(left, top, width, height);
		isClicked = false;
	}
	
	public void handleEvent(Vector2d mousePosition) {
		
		if( btnRegion.isInGeo(mousePosition)) {
			isClicked = true;
		}
	}
	
	public Boolean isClicked() {
		return isClicked;
	}
	public void setClickedFalse() {
		isClicked = false;
	}
	
	public void update() {
		if(isClicked) {
			currTexture = clickedTexture;
			clickedTexture=unclickTexture;
			unclickTexture=currTexture;

		}else {
			currTexture = unclickTexture;
		}
		
	}

	public void render(Graphics2D g) {
		currTexture.render(g);
	}
}
