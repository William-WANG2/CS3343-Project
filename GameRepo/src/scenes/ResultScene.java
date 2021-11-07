package scenes;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;

import game.GREGame;
import gameObject.Button;
import gameObject.GameResult;
import util.GlobalConstants;
import util.Mouse;

public class ResultScene extends Scene{
	
	private Boolean toNextScene;
	private Mouse mouse;
	Button restartButton;
	private GameResult gameResult;
	String[] groupMembers = {
			
			"GameFlow & Animation : Zhang Yuyang",
			"Algorithm : Wang Xuezhen",
			"Words IO  : Li Ruixin",
			"Character : Liu Mingyang",
			"Others    : Guo Shangping , He Ruozhen"
	};
	int stepCount;
	int correctCount;
	String[] vocabularies;
	
	private void handleEvent() {
		
		restartButton.handleEvent(mouse.mousePos);
		if(restartButton.isClicked())
		{
			toNextScene = true;
		}
	}
	
	
	@Override
	public void enter() {
		
		toNextScene = false;
		mouse = mApp.mouse;
		restartButton = new Button("res/textures/RestartButton.png", "res/textures/RestartButton.png", GlobalConstants.APP_WIDTH/2 - 100, 120, 180, 180);
		gameResult = ((GREGame)(mApp)).getGameResult();
	}

	@Override
	public void update() {
		
		handleEvent();
		restartButton.update();
		
	}

	@Override
	public void render(Graphics2D g) {
		g.setFont(new Font("Arial", Font.PLAIN, 30));
		g.drawString("Restart", GlobalConstants.APP_WIDTH/2 - 60, 220);
		restartButton.render(g);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
		int height = GlobalConstants.APP_HEIGHT - 120;
		for(String member : groupMembers) {
			g.drawString(member, 20, height);
			height += 20;
		}
		if(gameResult.isGameWin()) {

			g.drawString("You win!!!", 30, 30);
		}
		else {
			g.drawString("You lose!!!", 30, 30);
		}
	}

	@Override
	public void exit() {
		if(toNextScene) {
			mApp.loadScene(new PlayingScene());
		}
	}

}
