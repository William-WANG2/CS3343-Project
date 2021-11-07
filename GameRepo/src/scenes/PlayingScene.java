package scenes;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import game.GREGame;
import gameObject.Board;
import gameObject.Button;
import gameObject.Dio;
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
	private Texture background;
	private boolean toNextScene;
	private boolean isWin;
	private int correctCount;
	
	private int errorCount;

	@Override
	public void enter() {
		background = Texture.loadImage("res/background/basketballCourt.png", 0, 0, GlobalConstants.APP_WIDTH, GlobalConstants.APP_HEIGHT);
		
		map = Map.getInstance();
		map.initialize(GlobalConstants.MAP_ROW, GlobalConstants.MAP_COLUMN, 250, 250, GlobalConstants.APP_WIDTH/2, (int)(GlobalConstants.APP_HEIGHT * 0.55), WordType.getWordTypePath(((GREGame)mApp).getWordType())); 
		
		board = Board.getInstance();
		board.setBoardAppearance("res/textures/box.png", GlobalConstants.APP_WIDTH/2 - 20, 100, (int)(GlobalConstants.APP_WIDTH), 400);
		
		dio = Dio.getInstance();
		dio.enter(map.getMap()[map.getColRowCount().x / 2][map.getColRowCount().y / 2]);
		
		mouse = mApp.mouse;
		key = mApp.key;
		toNextScene = false;
		correctCount = 0;
		errorCount = 0;
	}

	@Override
	public void update() {
		if(dio.isEscape()) {
			toNextScene = true;
			isWin = false;
		}
		else if(!dio.isAlive()) {
			toNextScene = true;
			isWin = true;
		}
		
		map.update(mouse, key);
		dio.upadateAnimationSequencePerFrame();
		board.handleKeyboardInput(key);	
		MusicController.getInstance().update(mouse);
		
		mouse.mouseClicked = false;

	}

	@Override
	public void render(Graphics2D g) {
		AffineTransform transform = new AffineTransform(background.getScaleX(), 0.0, 0.0, background.getScaleY(), background.getPosX(), background.getPosY());
		g.drawImage(background.getImage(), transform, null);
		map.render(g);
		board.render(g);
		dio.render(g);
		MusicController.getInstance().render(g);
	}

	@Override
	public void exit() {
		if(toNextScene) {
			mApp.setGameResult(correctCount, errorCount, isWin);
			mApp.loadScene(new ResultScene());
		}
	}


}

