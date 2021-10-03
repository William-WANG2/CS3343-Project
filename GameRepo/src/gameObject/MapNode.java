package gameObject;

import java.util.ArrayList;

public class MapNode{
	
	private MapNodeInfo info;
	private ArrayList<MapNode> adjacency;
	
	public MapNode(int x, int y, int m, int n) {
		info = new MapNodeInfo(x, y, m, n);
	}
	//If the sprite is on the node, it can not be blocked.
	public boolean block() {
		if(info.activated) {
			return false;
		}
		else {
			info.blocked = true;
			return true;
		}
	}
	//If the node is currently blocked by the user -> can not occupied by the cat
	public boolean activate() {
		if(info.blocked) {
			return false;
		}
		else {
			info.activated = true;
			return true;
		}
	}
	public void addAdj(MapNode n) {
		adjacency.add(n);
	}
	public MapNodeInfo getState() {
		return info;
	}
}
