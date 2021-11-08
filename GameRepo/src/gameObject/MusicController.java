package gameObject;

import java.awt.Graphics2D;

import util.GlobalConstants;
import util.Mouse;
import util.Music;
import util.Vector2d;

public class MusicController {
	private GameButton musicToggle;
	private boolean change;
	private Music background;
	private Music good;
	private Music bad;
	private static MusicController instance = new MusicController();
	public static MusicController getInstance() {
		return instance;
	}
	private MusicController() {
		change=false;
		musicToggle = new GameButton("res/music/musicOn.png", "res/music/musicOff.png", 10, GlobalConstants.APP_HEIGHT/13, 50, 50);
		String filepath = "res/music/CXKisBeautiful.wav";
		background = new Music(filepath, -30);
		filepath = "res/music/good.wav";
		good = new Music(filepath, 0);
		filepath = "res/music/bad.wav";
		bad = new Music(filepath, 0);
	}
	

	public void startBackground() {
		background.loop();
	}
	
	private void updateBackgroundMusic(Vector2d mousePosition) {
		musicToggle.handleEvent(mousePosition);
		if(musicToggle.isClicked())
		{
			change = !change;
			if(change) {
				background.close();
			}
			else {
				background.loop();
			}
			musicToggle.update();
		}
	}

	public void handleClickEvent(Vector2d mousePosition) {
		updateBackgroundMusic(mousePosition);
	}
		
	
	public void renderBackgroundButton(Graphics2D g) {
		musicToggle.render(g); 
	}
	
	public void soundEffectWin() {
		good.start();
	}
	public void soundEffectLose() {
		bad.start();
	}
	public void soundEffectExit() {
		good.close();
		bad.close();
	}
}
