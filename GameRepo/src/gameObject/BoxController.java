package gameObject;
import gameObject.MapNode;

public class BoxController {
	
	private static BoxController controller = new BoxController();
	public static BoxController getInstance() {
		return controller;
	}

	//hover over the MapNode, show the MessageBox containing definition
	public void showDefine(MapNode n) {
		MessageBox.showDef(n);
		
	}
	
	// remove mouse, remove MessageBox
	public void removeDefine() {
		
	}	

	//click show the input window, if timeout or invalid input, spirit move
	public void allowInput() {
		
	}
}
