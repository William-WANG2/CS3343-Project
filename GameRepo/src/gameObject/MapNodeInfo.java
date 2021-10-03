package gameObject;

import util.*;

public class MapNodeInfo {
	//position displayed
	public Vector2d displayPos;
	//abstract position
	public Vector2d abstractPos;
	public boolean blocked; //the node can not be accessed since it is blocked by the user
	public boolean activated; //the node is currently occupied by the sprite
	public MapNodeInfo(int x, int y, int m, int n) {
		this.displayPos = new Vector2d(x, y);
		this.abstractPos = new Vector2d(m, n);
		this.activated = false;
		this.blocked = false;
	}
}
