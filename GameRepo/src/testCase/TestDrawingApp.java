package testCase;
import java.awt.Canvas;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import scenes.LoadingScene;
import util.*;

/*
 * by zyy
 */

public class TestDrawingApp extends GameApplication{
	
	@Override
	public void initialize() {
		
		super.initialize();
		currScene = new TestClickingButton();
		currScene.mApp = this;
		currScene.setupInput(this.canvas);
		currScene.enter();
		
		gameThread = new Thread(this);
		gameThread.start();
	}
		
}
