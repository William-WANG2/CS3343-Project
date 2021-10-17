package gameObject;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import keyValue.*;

public class BoxController implements KeyListener{
	
	private static BoxController controller = new BoxController();
	private static int fieldX = 400;
	private static int fieldY = 200;
	private static int fieldH = 100;
	private static int fieldW = 100;
	private static BoxMessage currMessage = null;
	private static BoxField currField = new BoxField(fieldX,fieldY,fieldH,fieldW);
	public static BoxController getInstance() {
		return controller;
	}
	
	public void setField(int x, int y, int w, int h) {
		currField=new BoxField(x,y,w,h);
	}
	
	public void enter() {
		currField.enter();
	}
	
	//show box
	public void render(Graphics2D g) {
		currField.update(currMessage.getMessage());
		currField.render(g);
		
	}
	//if currMessage change, update the field
	public void update(String m, int i) {//the integer is to indicate whether it is answer or define
		currMessage = new BoxMessage(m, i);		
	}

	//click show the input window, if timeout or invalid input, the spirit move
	public String userInput() {
		String in = "";
		return in;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char value = e.getKeyChar();
		
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
