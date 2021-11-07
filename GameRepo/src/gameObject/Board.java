package gameObject;

import gameObject.MapNode;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.ArrayList;

import util.Key;
import util.Mouse;
import util.Texture;

public class Board{
	
	private static Texture boardTexture;
	private static String currentInput = "";
	private static WordInfo currentWordInformation;
	
	static private Board instance = new Board();
	private Board() {	
	}
	
	public static Board getInstance() {
		return instance;
	}
	
	public void setBoardAppearance(String boxTexturePath, int centerX, int centerY, int width, int height){
		boardTexture = Texture.loadImage(boxTexturePath, centerX - width/2, centerY - height/2, width, height);
	}
	
	public void setWordInfo(WordInfo wordInformation) {
		currentWordInformation = wordInformation;
		currentInput = "";
	}

	public void handleKeyboardInput(Key key) {
		
		while(!key.queuingEvent.isEmpty()) {
			if(!MapNode.isViewExist()) {
				key.queuingEvent.clear();
				return;
			}
			int keyEvent = key.queuingEvent.getFirst();
			
			if(keyEvent == KeyEvent.VK_BACK_SPACE) {
				currentInput = currentInput.length() <= 1 ? "" : currentInput.substring(0, currentInput.length()-1);
			}
			else if(keyEvent >= KeyEvent.VK_A && keyEvent <= KeyEvent.VK_Z){
				currentInput += (char)(keyEvent - KeyEvent.VK_A+'a');
			}
			else if(keyEvent == KeyEvent.VK_ENTER) {
				MapNode.handleViewNodeInput();
				currentInput = "";
				currentWordInformation = null;
			}
			
			key.queuingEvent.removeFirst();
		}
	}
	
	public static boolean isCorrectAnswer() {
		return (currentInput.equals(currentWordInformation.getWord()));
	}
	
	public WordInfo getWordInfo() {
		return currentWordInformation;
	}
	
	public void render(Graphics2D g) {
		
		g.setFont(new Font("Dialog", Font.BOLD+Font.ITALIC, 24));
		AffineTransform transform = new AffineTransform(boardTexture.getScaleX(), 0.0, 0.0, boardTexture.getScaleY(), boardTexture.getPosX(), boardTexture.getPosY());
		g.drawImage(boardTexture.getImage(), transform, null);
		if(currentWordInformation != null)
		{
			g.drawString(currentInput, 400, 150);
			if(currentWordInformation != null) {
				g.drawString(currentWordInformation.getDefinition(), 400, 100);
			}
			
		}
	}
}
