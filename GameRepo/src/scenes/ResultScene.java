package scenes;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;

import game.GREGame;
import gameObject.Button;
import gameObject.GameResult;
import gameObject.MusicController;
import util.GlobalConstants;
import util.Mouse;
import util.Texture;

public class ResultScene extends Scene{
	
	private Boolean toNextScene;
	private Mouse mouse;
	private Button restartButton;
	private static boolean resHasLoaded = false; //indicate whether the pictures have been loaded, if yes, skip the loading process
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
			restartButton.handleEvent(mouse.mousePos);
			if(restartButton.isClicked())
			{
				toNextScene = true;
			}
		}
	}
	
	@Override
	public void enter() {
		/*load resources*/
		if(!resHasLoaded) {
			restartButton = new Button("res/endScene/Menu.png", "res/endScene/Menu2.png", GlobalConstants.APP_WIDTH/2 - 100, 120, 180, 180);
			kunkun = Texture.loadImage("res/endScene/endKunkun.png", 50, GlobalConstants.APP_HEIGHT/3, 500, 500);
			winMsg = Texture.loadImage("res/endScene/congrat.png", GlobalConstants.APP_WIDTH/2 - 500, 50, 1000, 200);	
			loseMsg = Texture.loadImage("res/endScene/lose.png", GlobalConstants.APP_WIDTH/2 - 500, 50, 1000, 200);
			ack = Texture.loadImage("res/endScene/ack.png", GlobalConstants.APP_WIDTH - 600, GlobalConstants.APP_HEIGHT/4, 500, 500);
			resHasLoaded = true;
		}
		
		restartButton = new Button("res/endScene/Menu.png", "res/endScene/Menu2.png", GlobalConstants.APP_WIDTH/2-100 , GlobalConstants.APP_HEIGHT/2, 150, 75);
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
	}

	@Override
	public void exit() {
		if(toNextScene) {
			mApp.loadScene(new LoginScene());
		}
	}

}
