package util;
import java.awt.Graphics2D;

public interface FrameUpdate {
	public abstract void enter();
	public abstract void render(Graphics2D g);
	public abstract void exit();
	public void update(Mouse mouse, Key key);
}
