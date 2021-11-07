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
	private static String showString;
	private static WordInfo currentWordInformation;

	static private Board instance = new Board();
	private Board() {	
	}
	
	public static Board getInstance() {
		return instance;
	}
	
	public void reset(String boxTexturePath, int centerX, int centerY, int width, int height){
		boardTexture = Texture.loadImage(boxTexturePath, centerX - width/2, centerY - height/2, width, height);
	}
	
	public void setWordInfo(WordInfo wordInformation) {
		currentWordInformation = wordInformation;
		currentInput = "";
	}
	
	public void handleInputDelete() {
		currentInput = currentInput.length() <= 1 ? "" : currentInput.substring(0, currentInput.length()-1);
	}
	public void handleInputLetter(char letter) {
		if(currentInput.length() < currentWordInformation.getWordLength()-currentWordInformation.getWordLength()/3*2){
			currentInput += letter;
		}		
	}
	
	public void handleInputFinish() {
		currentInput = "";
		currentWordInformation = null;
	}
	
	public boolean isCorrectAnswer() {
		return (currentInput.equals(currentWordInformation.getWord().substring(currentWordInformation.getWordLength()/3, currentWordInformation.getWordLength()-currentWordInformation.getWordLength()/3)));
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
			int existNum = currentWordInformation.getWordLength()/3;
			showString = currentWordInformation.getWord().substring(0, existNum);
			for(int i = 0; i < currentWordInformation.getWordLength()-existNum*2; i++) {
				showString += i < currentInput.length() ? (currentInput.charAt(i)) : "_ ";
			}
			showString+=currentWordInformation.getWord().substring(currentWordInformation.getWordLength()-existNum,currentWordInformation.getWordLength());
			g.drawString(showString, 400, 150);
			if(currentWordInformation != null) {
				g.drawString(currentWordInformation.getDefinition(), 400, 100);
			}
		}
	}
}
