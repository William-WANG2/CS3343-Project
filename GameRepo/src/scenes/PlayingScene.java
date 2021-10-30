package scenes;

import java.awt.Graphics2D;
import gameObject.Board;
import gameObject.Dio;
import gameObject.Map;
import util.*;

public class PlayingScene extends Scene{

	Mouse mouse;
	Key key;
	Map map;
	Board board;
	Dio dio;
	boolean toNextScene;
	boolean win;
	int correctCount;
	int stepCount;
	
	@Override
	public void enter() {
		
		map = Map.getInstance();
		map.initialize(GlobalConstants.MAP_ROW, GlobalConstants.MAP_COLUMN, 300, 300, GlobalConstants.APP_WIDTH/2, (int)(GlobalConstants.APP_HEIGHT * 0.6), "res/word.txt"); 
		
		board = Board.getInstance();
		board.setBoard("res/textures/box.png", GlobalConstants.APP_WIDTH/2, 50, (int)(GlobalConstants.APP_WIDTH * 0.8), 200);
		
		dio = Dio.getInstance();
		dio.initialize(map.getMap()[map.getColRowCount().x / 2][map.getColRowCount().y / 2]);
		
		mouse = mApp.mouse;
		key = mApp.key;
		toNextScene = false;
		correctCount = 0;
		stepCount = 0;
	}

	@Override
	public void update() {
		if(dio.isEscape()) {
			toNextScene = true;
			win = false;
		}
		else if(!dio.isAlive()) {
			toNextScene = true;
			win = true;
		}
		map.update(mouse, key);
		board.update(key);
	}

	@Override
	public void render(Graphics2D g) {
		if(toNextScene) {
			g.clearRect(0, 0, GlobalConstants.APP_WIDTH, GlobalConstants.APP_HEIGHT);
			mApp.loadScene(new ResultScene());
		}
		else {
			map.render(g);
			board.render(g);
			dio.render(g);
		}
	}

	@Override
	public void exit() {
		
	}


}

