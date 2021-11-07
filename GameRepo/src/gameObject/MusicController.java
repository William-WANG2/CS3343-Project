package gameObject;

import java.awt.Graphics2D;

import util.GlobalConstants;
import util.Mouse;
import util.Music;

public class MusicController {
	private Button musicToggle;
	private boolean change;
	private static MusicController instance = new MusicController();
	public static MusicController getInstance() {
		return instance;
	}
	private MusicController() {
		change=false;
		musicToggle = new Button("res/music/musicOn.png", "res/music/musicOff.png", GlobalConstants.APP_WIDTH/15, GlobalConstants.APP_HEIGHT/15, 50, 50);
	}
	
	private void handleMusic(Mouse mouse) {
		musicToggle.handleEvent(mouse);
		if(musicToggle.isClicked())
		{
			change = !change;
			if(change) {
				Music.close();
			}
			else {
				Music.open();
			}
		}
	}
	
	public void update(Mouse mouse) {
		handleMusic(mouse);
		musicToggle.update();
		musicToggle.setClickedFalse();
	}
	
	public void render(Graphics2D g) {
		musicToggle.render(g); 
	}
}
