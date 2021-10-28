package util;

import java.util.LinkedList;

public class Key {
	public boolean keyPressed;
	public LinkedList<Character> queuingChars;
	public Key() {
		queuingChars = new LinkedList<Character>();
	}
}
