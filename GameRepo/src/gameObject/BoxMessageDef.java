package gameObject;

public class BoxMessageDef extends BoxMessage {
	public BoxMessageDef(String m) {
		super(m);
	}

	public BoxMessageDef() {
		super();
	}

	@Override
	public String getMessage() {
		return message;
	}
	
}
