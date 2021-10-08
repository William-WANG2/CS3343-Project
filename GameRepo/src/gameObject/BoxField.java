package gameObject;

import javax.swing.JPanel;

import keyValue.Info;

public class BoxField extends JPanel {
	
	private int x,y;
	private int height,width;
	public BoxField(int x, int y, int h, int w) {
		this.x=x;
		this.y=y;
		this.height=h;
		this.width=w;
	}
	
	//hover over the MapNode, show the MessageBox containing definition
	public void render(BoxMessage m) {
		
	}
}
