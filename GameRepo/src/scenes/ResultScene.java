package scenes;

import java.awt.Font;
import java.awt.Graphics2D;

import game.GREGame;
import game.GameSettingConstants;
import gameObject.GameButton;
import gameObject.GameResult;
import gameObject.MusicController;
import util.Mouse;
import util.Texture;

public class ResultScene extends Scene{
	
	/*All resources used in this scene*/
	private GameButton restartButton;
	private static Texture kunkun;
	private static Texture winMsg;
	private static Texture loseMsg;
	private static Texture ack;
	private GameResult gameResult;
	int stepCount;
	int correctCount;
	String[] vocabularies;
	
	
	private void handleMouseClick(Mouse mouse) {
		if(mouse.isClicked) {
			MusicController.getInstance().handleClickEvent(mouse.mousePos);
			restartButton.handleEvent(mouse.mousePos);
			if(restartButton.isClicked())
			{
				toNextScene = true;
			}
			mouse.isClicked = false;
		}
	}
	
	@Override
	public void enter() {
		/*load resources*/
		if(!resHasLoaded) {
			kunkun = Texture.loadImage("res/endScene/endKunkun.png", 50, GameSettingConstants.APP_HEIGHT/3, 500, 500);
			winMsg = Texture.loadImage("res/endScene/congrat.png", GameSettingConstants.APP_WIDTH/2 - 500, 50, 1000, 200);	
			loseMsg = Texture.loadImage("res/endScene/lose.png", GameSettingConstants.APP_WIDTH/2 - 500, 50, 1000, 200);
			ack = Texture.loadImage("res/endScene/ack.png", GameSettingConstants.APP_WIDTH - 600, GameSettingConstants.APP_HEIGHT/4, 500, 500);
			resHasLoaded = true;
		}
		
		restartButton = new GameButton("res/Button/Restart.png", "res/Button/Restart2.png", GameSettingConstants.APP_WIDTH/2-100 , GameSettingConstants.APP_HEIGHT/2, 150, 75);
		toNextScene = false;
		mouse = mApp.mouse;
		gameResult = ((GREGame)(mApp)).getGameResult();
	}

	@Override
	public void update() {
		handleMouseClick(mouse);
		restartButton.update();
	}

	@Override
	public void render(Graphics2D g) {
		g.setFont(new Font("Dialog", Font.BOLD+Font.ITALIC, 30));
		kunkun.render(g);
		restartButton.render(g);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
		if(gameResult.isGameWin()) {
			winMsg.render(g);
		}
		else {
			loseMsg.render(g);
		}
		ack.render(g);
		MusicController.getInstance().renderBackgroundButton(g);
	}

	@Override
	public void exit() {
		if(toNextScene) {
			mApp.loadScene(new LoginScene());
		}
	}

}
