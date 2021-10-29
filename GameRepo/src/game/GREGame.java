package game;

import util.*;
import scenes.*;

public class GREGame extends GameApplication{
	
	public GREGame() {
	}
	
	public void initialize() {
		
		super.initialize();
		
		loadScene(new LoginScene());
		gameThread = new Thread(this);
		gameThread.start();
	}
}