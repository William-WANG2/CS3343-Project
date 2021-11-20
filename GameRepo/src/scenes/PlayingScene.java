package scenes;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import game.GREGame;
import game.GameSettingConstants;
import gameObject.Board;
import gameObject.Character;
import gameObject.GameResult;
import gameObject.Map;
import gameObject.MapNode;
import gameObject.MusicController;
import gameObject.EnumVocabularyBook;

import util.*;

public class PlayingScene extends Scene{


	private Key key;
	private Map map;
	private Board board;
	private Character dio;
	private static Texture background;
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
			background = Texture.loadImage("res/background/basketballCourt.png", 0, 0, GameSettingConstants.APP_WIDTH, GameSettingConstants.APP_HEIGHT);
			resHasLoaded = true;
		}
			
		map = Map.getInstance();
		map.initialize(GameSettingConstants.MAP_ROW, GameSettingConstants.MAP_COLUMN, 250, 250, GameSettingConstants.APP_WIDTH/2, (int)(GameSettingConstants.APP_HEIGHT * 0.55), EnumVocabularyBook.getWordTypePath(((GREGame)mApp).getBookType())); 
		
		dio = Character.getInstance();
		dio.enter(map.getMap()[map.getColRowCount().x / 2][map.getColRowCount().y / 2]);
		
		board = Board.getInstance();
		board.reset("res/textures/box.png", GameSettingConstants.APP_WIDTH/2 - 20, 100, (int)(GameSettingConstants.APP_WIDTH), 400);
		
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

