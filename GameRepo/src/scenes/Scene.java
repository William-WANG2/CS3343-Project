package scenes;

import java.awt.Graphics2D;

import game.GREGame;

public abstract class Scene {
	
	public GREGame mApp;
	private static boolean next;
	
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
