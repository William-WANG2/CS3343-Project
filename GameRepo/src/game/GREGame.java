package game;

import util.*;

import gameObject.GameResult;
import gameObject.WordType;
import scenes.*;

public class GREGame extends GameApplication{
	
	//These are for score statistics
	private GameResult result;
	private WordType wordType;
	
	public GREGame() {
	}
	
	public void setGameResult(GameResult result) {
		this.result = result;
	}
	public GameResult getGameResult() {
		return result;
	}
	public WordType getWordType()
	{
		return wordType;
	}
	public void setWordType(WordType w) {
		wordType = w;
	}
	public void initialize() {
		
		super.initialize();
		loadScene(new LoginScene());
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void loadScene(Scene next) {
		currScene = next;
		currScene.mApp = this;
		currScene.enter();
	}
	
	
}