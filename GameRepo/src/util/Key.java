package util;

import java.util.LinkedList;

public class Key {
	public boolean deletePressed;
	public boolean enterPressed;
	public LinkedList<Character> queuingChars;
	public Key() {
		queuingChars = new LinkedList<Character>();
		deletePressed = false;
	}
}
