package util;
/*
 * Transform is a wrapped position and scale information
 */
public class Transform {

	public Vector2f position;
	public Vector2f scale;
	
	public Transform() {
		this.position = new Vector2f(0.0f, 0.0f);
		this.scale = new Vector2f(1.0f, 1.0f);
	}
	
	public Transform(Vector2f pos, Vector2f scale) {
		this.position = pos;
		this.scale = scale;
	}
}
