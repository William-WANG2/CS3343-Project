package scenes;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import game.GREGame;
import game.GameSettingConstants;
import gameObject.GameButton;
import gameObject.MusicController;
import gameObject.EnumVocabularyBook;
import util.*;
public class LoginScene extends Scene {

	/*All resources used in this scene*/
	private boolean isRuleNextScene;
	private EnumVocabularyBook modeIndex; //indicate which mode(sort of words) we proceed in next scene
	private GameButton startButtons[] = new GameButton[GameSettingConstants.NUM_GAME_MODE];
	private GameButton rule;
	private static Texture[] cxk;
	private static Texture logo;
	private int sequenceIndex;
	private long timeElapsed;
	private GameTimer timer = GameTimer.getInstance();;
	
	//Multi threads utilities for resource loading
	private ExecutorService threadPool;
	private List<Callable<Boolean>> loadTasks;
	
	//Mouse input handler for this scene
	private void handleMouseClick(Mouse mouse) {
		
		if(mouse.isClicked) {
			MusicController.getInstance().handleClickEvent(mouse.mousePos);
			rule.handleEvent(mouse.mousePos);
			for(int i=0; i<GameSettingConstants.NUM_GAME_MODE; i++) {
				startButtons[i].handleEvent(mouse.mousePos);
			}
			mouse.isClicked = false;
		}
		
		for(int i=0; i<GameSettingConstants.NUM_GAME_MODE; i++) {
			if(startButtons[i].isClicked())
			{
				toNextScene = true;
				modeIndex = EnumVocabularyBook.IntToWordType(i);
			}
		}
		if(rule.isClicked()) {
			isRuleNextScene = true;
		}
	}
	
	//To load animation sequence per thread
	private void load(int start) {
		loadTasks.add(new Callable<Boolean>() {
		@Override
		public Boolean call() throws Exception{
			for(int i = start; i < start+14; i++) {
				String path = String.format("res/animation/caixukun%d.jpg", i + 1);
				cxk[i] = Texture.loadImage(path, 400, 30, GameSettingConstants.APP_WIDTH, GameSettingConstants.APP_HEIGHT - 30);
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
		for(int i = 0; i < GameSettingConstants.NUM_GAME_MODE; i++) {
			String path1 = String.format("res/Button/StartButton%d.png", i + 1);
			String path2 = String.format("res/Button/StartButtonClicked%d.png", i + 1);
			startButtons[i] = new GameButton(path1, path2, GameSettingConstants.APP_WIDTH/3 + 50, (int)(GameSettingConstants.APP_HEIGHT/1.4) - 100 * i, 150, 75);
		}
		
		/*load rule button*/
		rule = new GameButton("res/Button/Rule.png", "res/Button/Rule2.png", GameSettingConstants.APP_WIDTH/3 + 50, (int)(GameSettingConstants.APP_HEIGHT/1.4) - 400, 150, 75);
		
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
		for(int i=0; i<GameSettingConstants.NUM_GAME_MODE; i++) {
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
		if(cxk[sequenceIndex]!=null) {
			cxk[sequenceIndex].render(g);
		}
		
		logo.render(g);
		for(int i=0; i<GameSettingConstants.NUM_GAME_MODE; i++) {
			startButtons[i].render(g);
		}
		rule.render(g);
		MusicController.getInstance().renderBackgroundButton(g);
	}

	@Override
	public void exit() {
		if(toNextScene) {
			mouse.isClicked = false;
			((GREGame)mApp).setBookType(modeIndex);
			mApp.loadScene(new PlayingScene());
		}
		if(isRuleNextScene) {
			mApp.loadScene(new RuleScene());
		}
	}
}
