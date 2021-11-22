package scenes;

import java.awt.Graphics2D;

import game.GREGame;
import util.Key;
import util.Mouse;

public abstract class Scene {
	
	public GREGame mApp;
	protected Mouse mouse;
	protected Key key;
	protected Boolean toNextScene;
	private static boolean next;
	protected boolean resHasLoaded = false; //indicate whether the resources have been loaded, if yes, skip the loading process
	
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
