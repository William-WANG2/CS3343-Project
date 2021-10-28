package gameObject;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import keyValue.*;

public class BoxController{
	
	private static BoxController controller = new BoxController();
	private static int fieldX = 400;
	private static int fieldY = 200;
	private static int fieldH = 100;
	private static int fieldW = 200;
	private static BoxMessage currMessage = new BoxMessageDef();
	private static BoxField currField = new BoxField(fieldX,fieldY,fieldW,fieldH);
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
		currField.render(g);
		
	}
	//if currMessage change, update the field
	public void update(String m, int i) {//the integer is to indicate whether it is answer or define
		if(i==0) {
			currMessage = new BoxMessageDef(m);
		}
		else {
			currMessage = new BoxMessageAns(m);
		}
		currField.update(currMessage.getMessage());
	}

	//click show the input window, if timeout or invalid input, the spirit move
	public boolean checkInput(){
//		boolean match=false;
//		char usrInput =  getUsrIn();
//		match = (currMessage.getBlank() == usrInput);
//		return match;
	}

	public char getUsrIn() {
		char res = 'a';
		//try {
		//	res=(char) System.in.read();
		//} catch (IOException e) {
		//	e.printStackTrace();
		//}
		return res;
	}

}
