package gameObject;

public class MapNodeInfo {
	public int x;
	public int y;
	public boolean blocked; //the node can not be accessed since it is blocked by the user
	public boolean activated; //the node is currently occupied by the sprite
	public MapNodeInfo(int x, int y) {
		this.x = x;
		this.y = y;
		this.activated = false;
		this.blocked = false;
	}
}
