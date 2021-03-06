package gameObject;

import java.awt.Graphics2D;

import game.GameSettingConstants;
import util.Music;
import util.Vector2d;

public class MusicController {
	private GameButton musicToggle;
	private boolean isOff;
	private Music background;
	private Music good;
	private Music bad;
	private static MusicController instance = new MusicController();
	public static MusicController getInstance() {
		return instance;
	}
	
	private MusicController() {
		isOff=false;
		musicToggle = new GameButton("res/music/musicOn.png", "res/music/musicOff.png", 10, GameSettingConstants.APP_HEIGHT/13, 50, 50);
		String filepath = "res/music/CXKisBeautiful.wav";
		background = new Music(filepath, -30);
		filepath = "res/music/good.wav";
		good = new Music(filepath, 0);
		filepath = "res/music/bad.wav";
		bad = new Music(filepath, 0);
	}
	
	public void startBackground() {
		if(!isOff) {
			background.loop();
		}
	}
	
	private void updateBackgroundMusic(Vector2d mousePosition) {
		musicToggle.handleEvent(mousePosition);
		if(musicToggle.isClicked())
		{
			isOff = !isOff;
			if(isOff) {
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
