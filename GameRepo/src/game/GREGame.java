package game;

import util.*;
import scenes.*;

public class GREGame extends GameApplication{
	
	public GREGame() {
	}
	
	public void initialize() {
		
		super.initialize();
		currScene = new LoginScene();
		currScene.mApp = this;
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	
}//