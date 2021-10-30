package scenes;

import java.awt.Graphics2D;

public class ResultScene extends Scene{
	boolean win;
	ResultScene(boolean win) {
		this.win = win;
	}
	@Override
	public void enter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		if(win) {
			g.drawString("You win!!!", 30, 30);
		}
		else {
			g.drawString("You lose!!!", 30, 30);
		}
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

}
