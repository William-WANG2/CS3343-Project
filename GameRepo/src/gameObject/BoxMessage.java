package gameObject;
//what to be shown in the field

public abstract class BoxMessage{
	protected String message;
	
	public BoxMessage() {
		this.message = "";
	}

	public BoxMessage(String m) {
		this.message=m;
	}

	public abstract String getMessage();
}