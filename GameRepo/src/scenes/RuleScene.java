package scenes;

import java.awt.Graphics2D;

import game.GameSettingConstants;
import gameObject.GameButton;
import gameObject.MusicController;
import util.Mouse;
import util.Texture;

public class RuleScene extends Scene {
	
	private static Texture rule;
	private GameButton menu;
	
	
	private void handleMouseClick(Mouse mouse) {
		if(mouse.isClicked) {
			MusicController.getInstance().handleClickEvent(mouse.mousePos);
			menu.handleEvent(mouse.mousePos);
			mouse.isClicked = false;
		}
		if(menu.isClicked())
		{
			toNextScene = true;
		}
	}
	
	@Override
	public void enter() {
		/*load resources*/
		if(!resHasLoaded) {
			rule = Texture.loadImage("res/rule.png", 0, 0, GameSettingConstants.APP_WIDTH, GameSettingConstants.APP_HEIGHT-200);
			resHasLoaded = true;
		}
		menu = new GameButton("res/Button/Menu.png", "res/Button/Menu2.png", GameSettingConstants.APP_WIDTH - 155 , GameSettingConstants.APP_HEIGHT - 300, 150, 75);
		toNextScene = false;
		mouse = mApp.mouse;
	}

	@Override
	public void update() {
		handleMouseClick(mouse);
		menu.update();
	}

	@Override
	public void render(Graphics2D g) {
		rule.render(g);
		menu.render(g);
		MusicController.getInstance().renderBackgroundButton(g);
	}

	@Override
	public void exit() {
		if(toNextScene) {
			mApp.loadScene(new LoginScene());
		}
	}

}
