package util;

public class NodeInfo {
	public int x;
	public int y;
	public boolean blocked; //the node can not be accessed since it is blocked by the user
	public boolean activated; //the node is currently occupied by the sprite
	public NodeInfo(int x, int y, boolean activated) {
		this.x = x;
		this.y = y;
		this.activated = activated;
	}
}
