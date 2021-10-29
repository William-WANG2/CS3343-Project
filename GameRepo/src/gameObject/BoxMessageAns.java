package gameObject;

import util.Key;

public class BoxMessageAns extends BoxMessage {
	private String input = "";
	public BoxMessageAns(String m) {
		super(m);
	}
	public BoxMessageAns() {
		super();
	}
	@Override
	public String getMessage() {
		return genAns();
	}
	private String genAns() {
		String res="Please input your answer for the blank: \n";
		res=res+input+"_";
		//generate the input request
		return res;
	}
	public void updateInput(Key key) { 
		//if delete key is pressed
		if(key.deletePressed) {
			//there are some buffered keys in user defined Key object
			if(!key.queuingChars.isEmpty()) {
				key.queuingChars.removeLast();
			}
			else {
				try {
					input = input.substring(0, input.length()-1);
				}
				catch(IndexOutOfBoundsException e) { //if the substring is empty
					input = "";
				}
			}
			key.deletePressed = true;
		}
		else {
			while(!key.queuingChars.isEmpty()) {
				input = input + key.queuingChars.removeFirst();
			}
		}
	}
	public boolean isInputValid() {
		return input.equals(message);
	}
}
