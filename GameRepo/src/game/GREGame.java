package game;

import util.*;

import gameObject.GameResult;
import gameObject.EnumVocabularyBook;
import scenes.*;

/*
 * The specific game we made. 
 */
public class GREGame extends GameApplication{
	
	private GameResult result;
	private EnumVocabularyBook vocabularyBookType;
	
	public GREGame() {
	}
	
	public void setGameResult(GameResult result) {
		this.result = result;
	}
	
	public GameResult getGameResult() {
		return result;
	}
	
	public EnumVocabularyBook getBookType(){
		return vocabularyBookType;
	}
	
	public void setBookType(EnumVocabularyBook b) {
		vocabularyBookType = b;
	}
	
	public void initialize() {
		super.initialize();
		//The first scene of our game.
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