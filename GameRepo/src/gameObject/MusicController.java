package gameObject;

import java.awt.Graphics2D;

import util.GlobalConstants;
import util.Mouse;
import util.Music;
import util.Vector2d;

public class MusicController {
	private Button musicToggle;
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
		musicToggle = new Button("res/music/musicOn.png", "res/music/musicOff.png", GlobalConstants.APP_WIDTH/15, GlobalConstants.APP_HEIGHT/15, 50, 50);
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
	
	private void handleBackgroundMusic(Vector2d mousePosition) {
		musicToggle.handleEvent(mousePosition);
		if(musicToggle.isClicked())
		{
			musicToggle.update();
			change = !change;
			if(change) {
				background.close();
			}
			else {
				background.loop();
			}
			musicToggle.setClickedFalse();
		}
	}

	public void handleClickEvent(Vector2d mousePosition) {
		handleBackgroundMusic(mousePosition);
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
