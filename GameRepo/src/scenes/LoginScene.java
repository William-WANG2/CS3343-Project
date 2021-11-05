package scenes;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import game.GREGame;
import gameObject.Button;
import gameObject.WordType;
import util.*;
import util.Texture;
public class LoginScene extends Scene {

	private Boolean toNextScene;
	private WordType modeIndex; //indicate which mode(sort of words) we proceed in next scene
	private Mouse mouse;
	private Button startButtons[] = new Button[GlobalConstants.NUM_GAME_MODE];
	private Texture[] cxk;
	private int sequenceIndex;
	private long timeElapsed;
	private GameTimer timer = GameTimer.getInstance();;
	private void handleEvent(Mouse mouse) {
		for(int i=0; i<GlobalConstants.NUM_GAME_MODE; i++) {
			if(startButtons[i].isClicked())
			{
				toNextScene = true;
				modeIndex = WordType.fromIntToWordType(i);
			}
		}
	}
	
	@Override
	public void enter() {
		
		toNextScene = false;
		mouse = mApp.mouse;
		for(int i = 0; i < GlobalConstants.NUM_GAME_MODE; i++) {
			String path1 = String.format("res/VocabularyButton/StartButton%d.png", i + 1);
			String path2 = String.format("res/VocabularyButton/StartButtonClicked%d.png", i + 1);
			startButtons[i] = new Button(path1, path2, GlobalConstants.APP_WIDTH/2 - 100, GlobalConstants.APP_HEIGHT/2 - 90 * i, 150, 75);
		}
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
		
		for(int i=0; i<GlobalConstants.NUM_GAME_MODE; i++) {
			startButtons[i].handleEvent(mouse);
			startButtons[i].update();
		}
		handleEvent(mouse);
		
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
			((GREGame)mApp).setWordType(modeIndex);
			mApp.loadScene(new PlayingScene());
		}else {
			AffineTransform transform = new AffineTransform(cxk[sequenceIndex].getScaleX(), 0.0, 0.0, cxk[sequenceIndex].getScaleY(), cxk[sequenceIndex].getPosX(), cxk[sequenceIndex].getPosY());
			g.drawImage(cxk[sequenceIndex].getImage(), transform, null);
			for(int i=0; i<GlobalConstants.NUM_GAME_MODE; i++) {
				startButtons[i].render(g);
			}
		}
	}

	@Override
	public void exit() {
		
	}
}
