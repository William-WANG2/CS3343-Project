package gameObject;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import keyValue.*;

public class BoxController implements KeyListener{
	
	private static BoxController controller = new BoxController();
	private BoxMessage currMessage = new BoxMessage();
	private BoxField currField = new BoxField(0,0,10,10);
	public static BoxController getInstance() {
		return controller;
	}
	
	public void updateMessage(BoxMessage m) {
		this.currMessage = m;
	}
	
	public void setField(int x, int y, int w, int h) {
		this.currField=new BoxField(x,y,w,h);
	}
	
	//show box
	public void render(Graphics2D g) {
		//add the boxfield panel to frame
		
	}
	//if currMessage change, update the field
	public void update() {
		currField.update(currMessage);
	}

	//click show the input window, if timeout or invalid input, the spirit move
	public void userInput() {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
