package scenes;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import testCase.TestClickingButton;
import testCase.TestDrawingScene;
import util.*;

public class LoginScene extends Scene {

	private Boolean toNextScene;
	private Mouse mouse;
	private Button startButton;
	
	private void handleEvent(Mouse muouse) {
		startButton.handleEvent(muouse);
		if(startButton.isClicked())
		{
			toNextScene = true;
		}
	}
	
	@Override
	public void enter() {
		
		toNextScene = false;
		mouse = mApp.mouse;
		startButton = new Button("res/textures/UIStartButton.png", "res/textures/UIStartButtonClicked.png", GlobalConstants.APP_WIDTH/2 - 130, GlobalConstants.APP_HEIGHT/2 - 150, 200, 200);
	}

	@Override
	public void update() {
		if(toNextScene) {
			mApp.loadScene(new PlayingScene());
		}
		handleEvent(mouse);
		startButton.update();
	}

	@Override
	public void render(Graphics2D g) {
		startButton.render(g);
		
	}

	@Override
	public void exit() {
		//do nothing
	}
}
