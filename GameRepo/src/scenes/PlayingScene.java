package scenes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import gameObject.BoxController;
import gameObject.Dio;
import gameObject.Map;
import scenes.Scene;
import util.*;

public class PlayingScene extends Scene{

	Mouse mouse;
	Key key;
	Map m;
	BoxController box;
	Dio dio;
	
	@Override
	public void enter() {
		
		m = Map.getInstance();
		m.initialize(10, 10, 200, 200, "./res/word.txt"); 
		m.enter();
		box = BoxController.getInstance();
		box.enter();
		dio = Dio.getInstance();
		dio.initialize(m.getMap()[4][5]);
		dio.enter();
		mouse = mApp.mouse;
		key = mApp.key;
	}

	@Override
	public void update() {

		m.update(mouse, key);

	}

	@Override
	public void render(Graphics2D g) {
		m.render(g);
		box.render(g);
		dio.render(g);
	}

	@Override
	public void exit() {
	}


}

