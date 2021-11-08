package scenes;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import game.GREGame;
import gameObject.GameButton;
import gameObject.MusicController;
import gameObject.WordType;
import util.*;
public class LoginScene extends Scene {

	private boolean isRuleNextScene;
	private WordType modeIndex; //indicate which mode(sort of words) we proceed in next scene
	private GameButton startButtons[] = new GameButton[GlobalConstants.NUM_GAME_MODE];
	private GameButton rule;
	private static Texture[] cxk;
	private static Texture logo;
	private int sequenceIndex;
	private long timeElapsed;
	private GameTimer timer = GameTimer.getInstance();;
	

	private ExecutorService threadPool;
	private List<Callable<Boolean>> loadTasks;
	
	private void handleMouseClick(Mouse mouse) {
		if(mouse.isClicked) {
			MusicController.getInstance().handleClickEvent(mouse.mousePos);
			rule.handleEvent(mouse.mousePos);
			for(int i=0; i<GlobalConstants.NUM_GAME_MODE; i++) {
				startButtons[i].handleEvent(mouse.mousePos);
			}
			mouse.isClicked = false;
		}
		
		for(int i=0; i<GlobalConstants.NUM_GAME_MODE; i++) {
			if(startButtons[i].isClicked())
			{
				toNextScene = true;
				modeIndex = WordType.fromIntToWordType(i);
			}
		}
		if(rule.isClicked()) {
			isRuleNextScene = true;
		}
	}
	
	private void load(int start) {
		loadTasks.add(new Callable<Boolean>() {
		@Override
		public Boolean call() throws Exception{
			for(int i = start; i < start+14; i++) {
				String path = String.format("res/animation/caixukun%d.jpg", i + 1);
				cxk[i] = Texture.loadImage(path, 400, 30, GlobalConstants.APP_WIDTH, GlobalConstants.APP_HEIGHT - 30);
			}
			return true;
		}
		});
	}
	@Override
	public void enter() {
		/*load resources*/
		if(!resHasLoaded) {
			/*load cxk*/
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
			/*load logo*/
			logo = Texture.loadImage("res/Logo.png", 100, 80, 400, 200);
			
			resHasLoaded = true;
		}
		
		/*load start buttons*/
		for(int i = 0; i < GlobalConstants.NUM_GAME_MODE; i++) {
			String path1 = String.format("res/Button/StartButton%d.png", i + 1);
			String path2 = String.format("res/Button/StartButtonClicked%d.png", i + 1);
			startButtons[i] = new GameButton(path1, path2, GlobalConstants.APP_WIDTH/3 + 50, (int)(GlobalConstants.APP_HEIGHT/1.4) - 100 * i, 150, 75);
		}
		
		/*load rule button*/
		rule = new GameButton("res/Button/Rule.png", "res/Button/Rule2.png", GlobalConstants.APP_WIDTH/3 + 50, (int)(GlobalConstants.APP_HEIGHT/1.4) - 400, 150, 75);
		
		toNextScene = false;
		isRuleNextScene = false;
		mouse = mApp.mouse;
		sequenceIndex = 0;
		timeElapsed = 0;
		
		/*start background music*/
		MusicController.getInstance().startBackground();
	}

	@Override
	public void update() {
		handleMouseClick(mouse);
		for(int i=0; i<GlobalConstants.NUM_GAME_MODE; i++) {
			startButtons[i].update();
		}
		rule.update();
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
		if(cxk[sequenceIndex]!=null)
			g.drawImage(cxk[sequenceIndex].getImage(), transform, null);
		
		logo.render(g);
		for(int i=0; i<GlobalConstants.NUM_GAME_MODE; i++) {
			startButtons[i].render(g);
		}
		rule.render(g);
		MusicController.getInstance().renderBackgroundButton(g);
	}

	@Override
	public void exit() {
		if(toNextScene) {
			((GREGame)mApp).setWordType(modeIndex);
			mApp.loadScene(new PlayingScene());
		}
		if(isRuleNextScene) {
			mApp.loadScene(new RuleScene());
		}
	}
}
