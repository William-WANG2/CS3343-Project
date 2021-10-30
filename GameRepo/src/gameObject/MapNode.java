package gameObject;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import keyValue.Info;
import util.FrameUpdate;
import util.Key;
import util.Mouse;
import util.Texture;

public class MapNode implements FrameUpdate{
	
	private MapNodeInfo info;
	private ArrayList<MapNode> adjacency;
	private static Texture grey = null;
	private static Texture orange = null;
	private static MapNode updateNode = null; //the node user is currently typing the answer for 
	public static MapNode getUpdateNode() {
		return updateNode;
	}

	private static MapNode viewNode = null; //the node user is currently viewing the definition 
	
	public MapNode(float x, float y, float r, int m, int n, Info gre) {
		info = new MapNodeInfo(x, y, r, m, n, gre);
		adjacency = new ArrayList<MapNode>();
	}

	public void addAdj(MapNode n) {
		adjacency.add(n);
	}
	public MapNodeInfo getState() {
		return info;
	}
	
	public ArrayList<MapNode> getAdjacency(){
		return adjacency;
	}
	public boolean isBorder() {
		return adjacency.contains(Map.getInstance().getDummy());
	}
	

	@Override
	public void enter() {
		//if the static variable is not loaded yet
		if(grey == null && orange == null) {
			String path = "res/circle/grey.png";
			grey = Texture.loadImage(path, 0, 0, (int)(2*info.radius), (int)(2*info.radius));
			path = "res/circle/orange.png";
			orange = Texture.loadImage(path, 0, 0, (int)(2*info.radius), (int)(2*info.radius));
		}
	}

	@Override
	public void update(Mouse mouse, Key key) {
		boolean isInGeo= Math.pow(mouse.mousePos.x - info.displayPos.y - info.radius,2) + Math.pow(mouse.mousePos.y - info.displayPos.x - info.radius, 2) < Math.pow(info.radius, 2);
		if(isInGeo && info.blocked==false) {//add restore the click if click another one
			//display the input region
			if(updateNode == null || updateNode == this) {
				//check if the answer is correct or not
				if(updateNode == this) {
					if(BoxController.getInstance().isInputValid()) {
						info.blocked = true;
						BoxController.getInstance().updateState("Well down!", 2);
						Dio.getInstance().update(mouse, key, false); //dio doesn't move if input is correct
					}
					else {
						BoxController.getInstance().updateState("Oh no!", 2);
						Dio.getInstance().update(mouse, key, true); //dio moves if input is wrong
					}
					updateNode = null;
					viewNode = null;
				}
				else if(viewNode==null || viewNode!=this) {
					viewNode = this;
					BoxController.getInstance().updateState(info.greInfo.getDefin(), 0);
				}
				else {
					BoxController.getInstance().updateState(info.greInfo.getAns(), 1);
					updateNode = this;
				}
			}
			mouse.mouseClicked = false;
		}
	}

	@Override
	public void render(Graphics2D g) {
		if(!info.blocked) {
			AffineTransform transform = new AffineTransform(grey.getScaleX(), 0.0, 0.0, grey.getScaleY(), info.displayPos.y, info.displayPos.x);
			g.drawImage(grey.getImage(), transform, null);
		}
		else {
			AffineTransform transform = new AffineTransform(orange.getScaleX(), 0.0, 0.0, orange.getScaleY(), info.displayPos.y, info.displayPos.x);
			g.drawImage(orange.getImage(), transform, null);
		}
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

}
