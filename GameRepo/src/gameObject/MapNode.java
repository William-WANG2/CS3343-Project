package gameObject;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import util.Texture;


public class MapNode{
	
	private MapNodeInfo nodeInfo;
	
	private ArrayList<MapNode> adjacency;
	private static Texture basketball = null;
	private static Texture selectedbasketball = null;
	private static Texture hole = null;
	private static MapNode updateNode = null; // the node user is currently typing the answer for

	public static MapNode getUpdateNode() {
		return updateNode;
	}

	private static MapNode viewNode = null; //the node user is currently viewing the definition 
	
	public static boolean isViewExist() {
		return (null != viewNode);
	}
	
	
	public MapNode(float x, float y, float r, int m, int n, WordInfo wordInformation) {
		nodeInfo = new MapNodeInfo(x, y, r, m, n, wordInformation);
		adjacency = new ArrayList<MapNode>();
	}

	public void addAdj(MapNode n) {
		adjacency.add(n);
	}

	public MapNodeInfo getNodeInformation() {
		return nodeInfo;
	}

	public ArrayList<MapNode> getAdjacency() {
		return adjacency;
	}
	
	//If the user input is correct, we should block the node
	public static void setViewNodeBlock() {
		viewNode.nodeInfo.blocked = true;
		Character.getInstance().recomputeShortestPath(false);
		viewNode = null;
	}
	
	//If the user input is incorrect, ignore.
	public static void setViewNodeNull() {
		Character.getInstance().recomputeShortestPath(true);
		viewNode = null;
	}
	
	public boolean isBorder() {
		return adjacency.contains(Map.getInstance().getDummy());
	}

	public void enter() {
		// if the static variable is not loaded yet
		if (basketball == null && hole == null) {
			String path = "res/textures/basketballOrigin.png";
			basketball = Texture.loadImage(path, 0, 0, (int) (2 * nodeInfo.radius), (int) (2 * nodeInfo.radius));
			path = "res/textures/basketballTrash.png";
			hole = Texture.loadImage(path, 0, 0, (int) (2 * nodeInfo.radius), (int) (2 * nodeInfo.radius));
			path = "res/textures/basketballHighlight.png";
			selectedbasketball = Texture.loadImage(path, 0, 0, (int) (2 * nodeInfo.radius), (int) (2 * nodeInfo.radius));
		}
	}
	
	public void handleClickEvent(int mousePositionX, int mousePositionY) {
		boolean isInGeo= Math.pow(mousePositionX - nodeInfo.displayPos.y - nodeInfo.radius,2) + Math.pow(mousePositionY - nodeInfo.displayPos.x - nodeInfo.radius, 2) < Math.pow(nodeInfo.radius, 2);
		if(isInGeo && nodeInfo.blocked==false) {//add restore the click if click another one
			//If the character is currently on the node, can not update it
			if(this != Character.getInstance().getNode()) {
				//set board
				if( this.nodeInfo.getWordInfo() != Board.getInstance().getWordInfo()) {
					Board.getInstance().setWordInfo(nodeInfo.getWordInfo());
				}
			}
			viewNode = this;
		}
	}
	
	
	public void render(Graphics2D g) {
		AffineTransform transform; //can not use Texture's render since it depends on the nodeInfo which is dynamic
		
		if(this == Character.getInstance().getNode())
		{
			return;
		}
		
		if(nodeInfo.blocked) {
			transform = new AffineTransform(hole.getScaleX(), 0.0, 0.0, hole.getScaleY(), nodeInfo.displayPos.y, nodeInfo.displayPos.x);
			g.drawImage(hole.getImage(), transform, null);
		} else {
			if (viewNode == this) {
				transform = new AffineTransform(selectedbasketball.getScaleX(), 0.0, 0.0, basketball.getScaleY(),
						nodeInfo.displayPos.y, nodeInfo.displayPos.x);
				g.drawImage(selectedbasketball.getImage(), transform, null);
			} else {
				transform = new AffineTransform(basketball.getScaleX(), 0.0, 0.0, basketball.getScaleY(),
						nodeInfo.displayPos.y, nodeInfo.displayPos.x);
				g.drawImage(basketball.getImage(), transform, null);
			}
		}
	}
}
