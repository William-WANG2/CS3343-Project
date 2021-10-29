package util;
import java.awt.Graphics2D;

public interface FrameUpdate {
	public abstract void enter();
	public abstract void render(Graphics2D g);
	public abstract void exit();
	//It makes no sense to accept mouse and key here
	public void update(Mouse mouse, Key key);
}
