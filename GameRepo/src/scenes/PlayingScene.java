package scenes;

import java.awt.Graphics2D;
import gameObject.BoxController;
import gameObject.Dio;
import gameObject.Map;
import util.*;

public class PlayingScene extends Scene{

	Mouse mouse;
	Key key;
	Map m;
	BoxController box;
	Dio dio;
	boolean toNextScene;
	boolean win;
	
	@Override
	public void enter() {
		m = Map.getInstance();
		m.initialize(GlobalConstants.MAP_ROW, GlobalConstants.MAP_COLUMN, 300, 300, "./res/word.txt"); 
		m.enter();
		box = BoxController.getInstance();
		box.enter();
		dio = Dio.getInstance();
		dio.initialize(m.getMap()[7][7]);
		dio.enter();
		mouse = mApp.mouse;
		key = mApp.key;
		toNextScene = false;
	}

	@Override
	public void update() {
		if(dio.isEscape()) {
			toNextScene = true;
			win = false;
		}
		else if(!dio.isAlive()) {
			toNextScene = true;
			win = true;
		}
		m.update(mouse, key);
		BoxController.getInstance().update(mouse, key);
	}

	@Override
	public void render(Graphics2D g) {
		if(toNextScene) {
			g.clearRect(0, 0, GlobalConstants.APP_WIDTH, GlobalConstants.APP_HEIGHT);
			mApp.loadScene(new ResultScene(win));
		}
		else {
			m.render(g);
			box.render(g);
			dio.render(g);
		}
	}

	@Override
	public void exit() {
		
	}


}

