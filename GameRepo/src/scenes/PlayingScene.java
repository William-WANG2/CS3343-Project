package scenes;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

import game.GREGame;
import gameObject.Board;
import gameObject.Button;
import gameObject.Dio;
import gameObject.GameResult;
import gameObject.Map;
import gameObject.MapNode;
import gameObject.MusicController;
import gameObject.WordType;

import util.*;

public class PlayingScene extends Scene{


	private Mouse mouse;
	private Key key;
	private Map map;
	private Board board;
	private Dio dio;
	private static boolean resHasLoaded = false; //indicate whether the pictures have been loaded, if yes, skip the loading process
	private static Texture background;
	private boolean toNextScene;
	private GameResult gameResult;
	
	private void handleMouseClick(Mouse mouse) {
		if(mouse.isClicked) {
			map.handleMouseClickEvent(mouse.mousePos);
			MusicController.getInstance().handleClickEvent(mouse.mousePos);
			mouse.isClicked = false;
		}
	}
	
	private void handleKeyboardInput(Key key) {
		
		//Clear all queued event if no active map node
		if(!MapNode.isViewExist()) {
			if(key.queuingEvent!=null) {
				key.queuingEvent.clear();
			}
			return;
		}
		
		//Handle queued event
		while(!key.queuingEvent.isEmpty()) {
			
			int keyEvent = key.queuingEvent.getFirst();
			
			if(keyEvent == KeyEvent.VK_BACK_SPACE) {
				board.handleInputDelete();
			}
			else if(keyEvent >= KeyEvent.VK_A && keyEvent <= KeyEvent.VK_Z){
				board.handleInputLetter((char)(keyEvent - KeyEvent.VK_A+'a'));
			}
			else if(keyEvent == KeyEvent.VK_ENTER) {
				if(board.isCorrectAnswer()) {
					MapNode.setViewNodeBlock();
					gameResult.increaseCorrectCount();
					MusicController.getInstance().soundEffectWin();
				}
				else {
					MapNode.setViewNodeNull();
					gameResult.increaseErrorCount();
					MusicController.getInstance().soundEffectLose();
				}
				board.handleInputFinish();
			}
			
			key.queuingEvent.removeFirst();
		}
	}
	
	@Override
	public void enter() {
		/*load resources*/
		if(!resHasLoaded) {
			background = Texture.loadImage("res/background/basketballCourt.png", 0, 0, GlobalConstants.APP_WIDTH, GlobalConstants.APP_HEIGHT);
			resHasLoaded = true;
		}
			
		map = Map.getInstance();
		map.initialize(GlobalConstants.MAP_ROW, GlobalConstants.MAP_COLUMN, 250, 250, GlobalConstants.APP_WIDTH/2, (int)(GlobalConstants.APP_HEIGHT * 0.55), WordType.getWordTypePath(((GREGame)mApp).getWordType())); 
		
		dio = Dio.getInstance();
		dio.enter(map.getMap()[map.getColRowCount().x / 2][map.getColRowCount().y / 2]);
		
		board = Board.getInstance();
		board.reset("res/textures/box.png", GlobalConstants.APP_WIDTH/2 - 20, 100, (int)(GlobalConstants.APP_WIDTH), 400);
		
		gameResult = new GameResult();
		
		mouse = mApp.mouse;
		key = mApp.key;
		toNextScene = false;
	}

	@Override
	public void update() {
		
		handleMouseClick(mouse);
		handleKeyboardInput(key);
		
		dio.upadateAnimationSequencePerFrame();
			
		if(dio.isEscape()) {
			toNextScene = true;
			gameResult.setIsWin(false);
		}
		else if(!dio.isAlive()) {
			toNextScene = true;
			gameResult.setIsWin(true);
		}
	}

	@Override
	public void render(Graphics2D g) {
		background.render(g);
		map.render(g);
		board.render(g);
		dio.render(g);
		MusicController.getInstance().renderBackgroundButton(g);
	}

	@Override
	public void exit() {
		if(toNextScene) {
			
			mApp.setGameResult(gameResult);
			mApp.loadScene(new ResultScene());
		}
	}
}

