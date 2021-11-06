package scenes;

import java.awt.Graphics2D;
import game.GREGame;
import gameObject.Board;
import gameObject.Button;
import gameObject.Dio;
import gameObject.Map;
import gameObject.MapNode;
import gameObject.WordType;

import util.*;



public class PlayingScene extends Scene{

	Mouse mouse;
	Key key;
	Map map;
	Board board;
	Dio dio;
	Button musicToggle;
    Button nomusic;
    
    
	boolean toNextScene;
	boolean isWin;
	int correctCount;
	int errorCount;
	int stepCount;
	
    boolean change;
    
    private void handleMusic(Mouse mouse) {
		musicToggle.handleEvent(mouse);
		if(musicToggle.isClicked())
		{
			change = !change;
			if(!change) {
				Music.close();
			}
			else {
				Music.open();
			}
		}
	}
   

	@Override
	public void enter() {
		change=true;
		musicToggle = new Button("res/textures/sound.jpg", "res/textures/nosound.jpg", GlobalConstants.APP_WIDTH/2 -630, GlobalConstants.APP_HEIGHT/2 - 150, 200, 200);
		
		
		map = Map.getInstance();
		map.initialize(GlobalConstants.MAP_ROW, GlobalConstants.MAP_COLUMN, 250, 250, GlobalConstants.APP_WIDTH/2, (int)(GlobalConstants.APP_HEIGHT * 0.55), WordType.getWordTypePath(((GREGame)mApp).getWordType())); 
		
		board = Board.getInstance();
		board.setBoardAppearance("res/textures/box.png", GlobalConstants.APP_WIDTH/2 - 20, 100, (int)(GlobalConstants.APP_WIDTH), 400);
		
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

		handleMusic(mouse);
		musicToggle.update();
		musicToggle.setClickedFalse();
		
		map.update(mouse, key);
		
		board.handleKeyboardInput(key);	
		
		mouse.mouseClicked = false;

	}

	@Override
	public void render(Graphics2D g) {
		map.render(g);
		board.render(g);
		dio.render(g);
		musicToggle.render(g); 
	}

	@Override
	public void exit() {
		if(toNextScene) {
			mApp.setGameResult(correctCount, errorCount, isWin);
			mApp.loadScene(new ResultScene());
		}
	}


}

