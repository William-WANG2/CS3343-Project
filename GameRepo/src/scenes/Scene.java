package scenes;

import util.GameApplication;

import java.awt.Canvas;
import java.awt.Graphics2D;

import game.GREGame;

public abstract class Scene {
	
	public GREGame mApp;
	
	public abstract void enter();
	public abstract void update();
	public abstract void render(Graphics2D g);
	public abstract void exit();
}
