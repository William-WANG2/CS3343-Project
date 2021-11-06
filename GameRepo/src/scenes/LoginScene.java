package scenes;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
	

	private ExecutorService threadPool;
	private List<Callable<Boolean>> loadTasks;
	
	private void handleEvent(Mouse mouse) {
		for(int i=0; i<GlobalConstants.NUM_GAME_MODE; i++) {
			if(startButtons[i].isClicked())
			{
				toNextScene = true;
				modeIndex = WordType.fromIntToWordType(i);
				startButtons[i].setClickedFalse();
			}

		}
	}
	
	private void load(int start) {
		loadTasks.add(new Callable<Boolean>() {
		@Override
		public Boolean call() throws Exception{
			for(int i = start; i < start+14; i++) {
				String path = String.format("res/animation/caixukun%d.jpg", i + 1);
				cxk[i] = Texture.loadImage(path, 0, 30, GlobalConstants.APP_WIDTH, GlobalConstants.APP_HEIGHT - 30);
			}
			return true;
		}
		});
	}
	@Override
	public void enter() {
		
		threadPool = Executors.newCachedThreadPool();
		loadTasks = new ArrayList<Callable<Boolean>>();
		
		cxk = new Texture[98];
		
		for(int i=0; i<91; i+=7) {
			load(i);
		}
		
		for(Callable<Boolean> task : loadTasks) {
			threadPool.submit(task);
		}
		threadPool.shutdown();
		
		toNextScene = false;
		mouse = mApp.mouse;

		for(int i = 0; i < GlobalConstants.NUM_GAME_MODE; i++) {
			String path1 = String.format("res/VocabularyButton/StartButton%d.png", i + 1);
			String path2 = String.format("res/VocabularyButton/StartButtonClicked%d.png", i + 1);
			startButtons[i] = new Button(path1, path2, GlobalConstants.APP_WIDTH/2 - 100, GlobalConstants.APP_HEIGHT/2 - 90 * i, 150, 75);
		}
		
		sequenceIndex = 0;
		timeElapsed = 0;
		while(true) {
			if(threadPool.isTerminated())
				break;
		}
		
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
		AffineTransform transform = new AffineTransform(cxk[sequenceIndex].getScaleX(), 0.0, 0.0, cxk[sequenceIndex].getScaleY(), cxk[sequenceIndex].getPosX(), cxk[sequenceIndex].getPosY());
		g.drawImage(cxk[sequenceIndex].getImage(), transform, null);
		for(int i=0; i<GlobalConstants.NUM_GAME_MODE; i++) {
			startButtons[i].render(g);
		}
	}

	@Override
	public void exit() {
		if(toNextScene) {
			((GREGame)mApp).setWordType(modeIndex);
			mApp.loadScene(new PlayingScene());
		}
	}
}
