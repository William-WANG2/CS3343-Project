package gameObject;
//what to be shown in the field

public class BoxMessage{
	private String message;
	private int i;//i=0 means define, i=1 means answer
	
	public BoxMessage() {
		this.message = "";
		this.i=-1;
	}

	public BoxMessage(String m, int i) {
		this.message=m;
		this.i=i;
	}

	public String getMessage() {
		if(i==0) {
			return message;
		}
		else if(i==1) {
			return genAns();
		}
		else {
			return " ";
		}
	}
	private String genAns() {
		String res="";
		//generate the input request
		return res;
	}
}