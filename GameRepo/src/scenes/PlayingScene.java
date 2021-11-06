package scenes;

import java.awt.Graphics2D;
import game.GREGame;
import gameObject.Board;
import gameObject.Dio;
import gameObject.Map;
import gameObject.WordType;
import util.*;

public class PlayingScene extends Scene{

	Mouse mouse;
	Key key;
	Map map;
	Board board;
	Dio dio;
	boolean toNextScene;
	boolean isWin;
	int correctCount;
	int errorCount;
	
	
	@Override
	public void enter() {
		
		map = Map.getInstance();
		map.initialize(GlobalConstants.MAP_ROW, GlobalConstants.MAP_COLUMN, 250, 250, GlobalConstants.APP_WIDTH/2, (int)(GlobalConstants.APP_HEIGHT * 0.55), WordType.getWordTypePath(((GREGame)mApp).getWordType())); 
		
		board = Board.getInstance();
		board.setBoard("res/textures/box.png", GlobalConstants.APP_WIDTH/2 - 20, 100, (int)(GlobalConstants.APP_WIDTH), 400);
		
		dio = Dio.getInstance();
		dio.initialize(map.getMap()[map.getColRowCount().x / 2][map.getColRowCount().y / 2]);
		
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
		board.update(key);
		mouse.mouseClicked = false;
	}

	@Override
	public void render(Graphics2D g) {
		map.render(g);
		board.render(g);
		dio.render(g);
	}

	@Override
	public void exit() {
		if(toNextScene) {
			mApp.setGameResult(correctCount, errorCount, isWin);
			mApp.loadScene(new ResultScene());
		}
	}


}

