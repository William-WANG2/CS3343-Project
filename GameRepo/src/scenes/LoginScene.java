package scenes;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import gameObject.Button;
import util.*;
import util.Texture;
public class LoginScene extends Scene {

	private Boolean toNextScene;
	private Mouse mouse;
	private Button startButton;
	private Texture[] cxk;
	private int sequenceIndex;
	private long timeElapsed;
	private GameTimer timer = GameTimer.getInstance();;
	
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
		startButton = new Button("res/textures/StartButton.png", "res/textures/StartButtonClicked.png", GlobalConstants.APP_WIDTH/2 - 130, GlobalConstants.APP_HEIGHT/2 - 150, 200, 200);
		cxk = new Texture[98];
		String path;
		for(int i = 0; i < 9; i++) {
			path = String.format("res/animation/caixukun 00%d.jpg", i + 1);
			cxk[i] = Texture.loadImage(path, 0, 30, GlobalConstants.APP_WIDTH, GlobalConstants.APP_HEIGHT - 30);
		}
		for(int i = 9; i < 98; i++) {
			path = String.format("res/animation/caixukun 0%d.jpg", i + 1);
			cxk[i] = Texture.loadImage(path, 0, 30, GlobalConstants.APP_WIDTH, GlobalConstants.APP_HEIGHT - 30);
		}
		
		sequenceIndex = -1;
		timeElapsed = 0;
		
	}

	@Override
	public void update() {
		
		handleEvent(mouse);
		startButton.update();
		
		timeElapsed += timer.DeltaTime();
		if(timeElapsed >= 300) {
			sequenceIndex++;
			sequenceIndex %= 98;
			timeElapsed -= 300;
		}
		
	}

	@Override
	public void render(Graphics2D g) {
		if(toNextScene) {
			mApp.loadScene(new PlayingScene());
		}else {
			
			AffineTransform transform = new AffineTransform(cxk[sequenceIndex].getScaleX(), 0.0, 0.0, cxk[sequenceIndex].getScaleY(), cxk[sequenceIndex].getPosX(), cxk[sequenceIndex].getPosY());
			g.drawImage(cxk[sequenceIndex].getImage(), transform, null);
			startButton.render(g);
		}
	}

	@Override
	public void exit() {
		
	}
}
