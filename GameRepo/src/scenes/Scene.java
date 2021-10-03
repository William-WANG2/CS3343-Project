package scenes;

import util.GameApplication;
import java.awt.Graphics2D;

public abstract class Scene {
	
	GameApplication mApp;
	
	public abstract void enter();
	public abstract void update();
	public abstract void render(Graphics2D g);
	public abstract void exit();
}
