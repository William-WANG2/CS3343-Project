package gameObject;
/*
 * Record the word information, which is its correct spell and definition
 */
public class WordInfo{
	
	private String word;
	private String wordDefinition;
	
	
	public WordInfo(String wordDefination, String word) {
		this.word = word;
		this.wordDefinition = wordDefination;
	}
	
	public String getDefinition() {
		return wordDefinition;
	}

	public String getWord() {
		return word;
	}
	
	public int getWordLength() {
		return word.length();
	}
}
