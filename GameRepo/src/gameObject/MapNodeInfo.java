package gameObject;

public class MapNodeInfo {
	//position displayed
	public int x;
	public int y;
	//abstract position
	public int m;
	public int n;
	public boolean blocked; //the node can not be accessed since it is blocked by the user
	public boolean activated; //the node is currently occupied by the sprite
	public MapNodeInfo(int x, int y, int m, int n) {
		this.x = x;
		this.y = y;
		this.m = m;
		this.n = n;
		this.activated = false;
		this.blocked = false;
	}
}
