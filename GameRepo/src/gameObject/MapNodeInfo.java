package gameObject;

import util.*;
import keyValue.*;

public class MapNodeInfo {
	//position displayed
	public Vector2f displayPos;
	//abstract position
	public Vector2d abstractPos;
	public float radius;
	public boolean blocked; //the node can not be accessed since it is blocked by the user
	public Info greInfo; //the key-value pair of GRE words
	
	public MapNodeInfo(float x, float y, float r, int m, int n, Info gre) {
		this.displayPos = new Vector2f(x, y);
		this.abstractPos = new Vector2d(m, n);
		this.blocked = false;
		this.radius = r;
		this.greInfo = gre;
	}
}
