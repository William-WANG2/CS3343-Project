package gameObject;
//what to be shown in the field

public class BoxMessage{
	private String message;
	
	public BoxMessage() {
		this.message = "";
	}

	public BoxMessage(String m) {
		this.message=m;
	}

	public String getMessage() {
		return message;
	}
}