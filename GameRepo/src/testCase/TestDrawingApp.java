package testCase;
import java.awt.Canvas;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.GREGame;
import scenes.LoadingScene;
import util.*;


import scenes.LoginScene;
/*
 * by zyy
 */
import scenes.Scene;

public class TestDrawingApp extends GameApplication{
	
	@Override
	public void initialize() {
		
		super.initialize();
		currScene = new LoginScene();
		//currScene.mApp = this;
		currScene.enter();
		
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void loadScene(Scene next) {
		 
		
	}
		
}
