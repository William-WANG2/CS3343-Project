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
	private static Texture basketball = null;
	private static Texture selectedbasketball = null;
	private static Texture hole = null;
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
		if(basketball == null && hole == null) {
			String path = "res/circle/basketball.png";
			basketball = Texture.loadImage(path, 0, 0, (int)(2*info.radius), (int)(2*info.radius));
			path = "res/circle/crack.png";
			hole = Texture.loadImage(path, 0, 0, (int)(2*info.radius), (int)(2*info.radius));
			path = "res/circle/selectedbasketball.png";
			selectedbasketball = Texture.loadImage(path, 0, 0, (int)(2*info.radius), (int)(2*info.radius));
		}
	}

	@Override
	public void update(Mouse mouse, Key key) {
		boolean isInGeo= Math.pow(mouse.mousePos.x - info.displayPos.y - info.radius,2) + Math.pow(mouse.mousePos.y - info.displayPos.x - info.radius, 2) < Math.pow(info.radius, 2);
		if(isInGeo && info.blocked==false) {//add restore the click if click another one
			
			Board.getInstance().receiveMessage(info.greInfo);
			//display the input region
			if(updateNode == null || updateNode == this) {
				//check if the answer is correct or not
				if(updateNode == this) {
					if(Board.isInputValid()) {
						info.blocked = true;
						Board.getInstance().updateState("Well down!", 2);
						Dio.getInstance().update(mouse, key, false); //dio doesn't move if input is correct
					}
					else {
						Board.getInstance().updateState("Oh no!", 2);
						Dio.getInstance().update(mouse, key, true); //dio moves if input is wrong
					}
					updateNode = null;
					viewNode = null;
				}
				else if(viewNode==null || viewNode!=this) {
					viewNode = this;
					Board.getInstance().updateState(info.greInfo.getDefin(), 0);
				}
				else {
					Board.getInstance().updateState(info.greInfo.getAns(), 1);
					updateNode = this;
				}
			}
			mouse.mouseClicked = false;
		}
	}

	@Override
	public void render(Graphics2D g) {
		AffineTransform transform;
		if(info.blocked) {
			transform = new AffineTransform(hole.getScaleX(), 0.0, 0.0, hole.getScaleY(), info.displayPos.y, info.displayPos.x);
			g.drawImage(hole.getImage(), transform, null);
		}
		else {
			if(viewNode == this) {
				transform = new AffineTransform(selectedbasketball.getScaleX(), 0.0, 0.0, basketball.getScaleY(), info.displayPos.y, info.displayPos.x);
				g.drawImage(selectedbasketball.getImage(), transform, null);
			}
			else {
				transform = new AffineTransform(basketball.getScaleX(), 0.0, 0.0, basketball.getScaleY(), info.displayPos.y, info.displayPos.x);
				g.drawImage(basketball.getImage(), transform, null);
			}
		}
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

}
