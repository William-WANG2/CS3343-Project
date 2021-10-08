package gameObject;

import util.*;

public class MapNodeInfo {
	//position displayed
	public Vector2f displayPos;
	//abstract position
	public Vector2d abstractPos;
	public float radius;
	public boolean blocked; //the node can not be accessed since it is blocked by the user
	public boolean activated; //the node is currently occupied by the sprite
	public MapNodeInfo(float x, float y, float r, int m, int n) {
		this.displayPos = new Vector2f(x, y);
		this.abstractPos = new Vector2d(m, n);
		this.activated = false;
		this.blocked = false;
		this.radius = r;
	}
}
