package scenes;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;

import gameObject.Button;
import util.GlobalConstants;
import util.Mouse;

public class ResultScene extends Scene{
	
	private Boolean toNextScene;
	private Mouse mouse;
	Button restartButton;
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
		
		restartButton.handleEvent(mouse);
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
		
	}

	@Override
	public void update() {
		
		handleEvent();
		restartButton.update();
		
	}

	@Override
	public void render(Graphics2D g) {
		
		if(toNextScene) {
			g.clearRect(0, 0, GlobalConstants.APP_WIDTH, GlobalConstants.APP_HEIGHT);
			mApp.loadScene(new PlayingScene());
		}else {
			g.setFont(new Font("Arial", Font.PLAIN, 30));
			g.drawString("Restart", GlobalConstants.APP_WIDTH/2 - 60, 220);
			restartButton.render(g);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
			int height = GlobalConstants.APP_HEIGHT - 120;
			for(String member : groupMembers) {
				g.drawString(member, 20, height);
				height += 20;
				
			if(true) {
				g.drawString("You win!!!", 30, 30);
			}
			else {
				g.drawString("You lose!!!", 30, 30);
				}
			}
		}
		
		
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

}
