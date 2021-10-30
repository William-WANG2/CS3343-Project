package gameObject;

import gameObject.MapNode;


import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import keyValue.*;
import util.FrameUpdate;
import util.Key;
import util.Mouse;
import util.Texture;

public class Board{
	
	private static Texture boardTexture;
	private static BoxMessage currMessage = new BoxMessageDef();
	
	static private Board instance = new Board();
	private Board() {	
	}
	
	public void setBoard(String boxTexturePath, int centerX, int centerY, int width, int height){
		boardTexture = Texture.loadImage(boxTexturePath, centerX - width/2, centerY - height/2, width, height);
	}
	
	public static Board getInstance() {
		return instance;
	}
	
	//if currMessage change, update the field
	public void updateState(String m, int i) { 
		//the integer is to indicate whether it is answer, definition or prompt
		if(i==0) {
			currMessage = new BoxMessageDef(m);
		}
		else if(i==1) {
			currMessage = new BoxMessageAns(m);
		}
		else {
			currMessage = new BoxMessagePrompt(m);
		}
		//currField.update(currMessage.getMessage()); This is a string
	}

	public void update(Key key) {
		if(currMessage instanceof BoxMessageAns) {
			((BoxMessageAns)currMessage).updateInput(key);
		}
	}
	public static boolean isInputValid() {
		if(currMessage instanceof BoxMessageAns) {
			return ((BoxMessageAns)currMessage).isInputValid();
		}
		return false;
	}
	
	public void render(Graphics2D g) {
		
		AffineTransform transform = new AffineTransform(boardTexture.getScaleX(), 0.0, 0.0, boardTexture.getScaleY(), boardTexture.getPosX(), boardTexture.getPosY());
		g.drawImage(boardTexture.getImage(), transform, null);
		g.drawString(currMessage.getMessage(), 400, 100);
		
	}
}
