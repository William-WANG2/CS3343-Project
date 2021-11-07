package gameObject;

import java.util.ArrayList;

public class GameResult {
	
	private boolean isWin;
	private int errorCount;
	private int correctCount;
	private int totalCount;
	private ArrayList<WordInfo> errorWords;
	
	public void reset() {
		errorCount = 0;
		correctCount = 0;
		totalCount = 0;
		errorWords = new ArrayList<WordInfo>();
	}
	
	public void increaseErrorCount() {
		errorCount++;
		totalCount++;
	}
	
	public void increaseCorrectCount() {
		correctCount++;
		totalCount++;
	}
	
	public void addErrorWords(WordInfo info) {
		errorWords.add(info);
	}
	
	public void setIsWin(boolean isWin) {
		this.isWin = isWin;
	}
	
	public boolean isGameWin() {
		return isWin;
	}
	
}
