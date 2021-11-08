package scenes;

import java.awt.Graphics2D;

import game.GREGame;
import util.Mouse;

public abstract class Scene {
	
	public GREGame mApp;
	protected Boolean toNextScene;
	protected Mouse mouse;
	private static boolean next;
	protected boolean resHasLoaded = false; //indicate whether the pictures have been loaded, if yes, skip the loading process
	
	public static void setNextScene(boolean n) {
		next = n;
	}
	public static boolean getNextScene() {
		return next;
	}
	
	public abstract void enter();
	public abstract void update();
	public abstract void render(Graphics2D g);
	public abstract void exit();
}
