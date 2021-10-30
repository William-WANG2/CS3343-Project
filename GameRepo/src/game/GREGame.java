package game;

import util.*;
import scenes.*;

public class GREGame extends GameApplication{
	
	//These are for score statistics
	private int correctCount;
	private int errorCount;
	private int stepCount;
	private boolean isWin;
	
	public GREGame() {
	}
	
	public void setGameResult(int correctCount, int errorCount, Boolean isWin) {
		this.correctCount = correctCount;
		this.errorCount = errorCount;
		this.isWin = isWin;
		this.stepCount = correctCount + errorCount;
	}
	
	public int getCorrectCount() {
		return correctCount;
	}
	public int getErrorCount() {
		return errorCount;
	}
	
	public Boolean isGameWin() {
		return isWin;
	}
	public int getStepCount() {
		return stepCount;
	}
	public void initialize() {
		
		super.initialize();
		
		loadScene(new LoginScene());
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void loadScene(Scene next) {
		
		if(null != currScene) {
			currScene.exit();
		}
		currScene = next;
		currScene.mApp = this;
		currScene.enter();
	}
	
	
}