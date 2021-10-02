package util;

public class Node {
	private NodeInfo info;
	public Node(int x, int y, boolean activated) {
		info = new NodeInfo(x, y, activated);
		info.blocked = false;
	}
	//If the sprite is on the node, it can not be blocked
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
	public NodeInfo getState() {
		return info;
	}
}
