package util;

public class Vector2f {
	public float x;
	public float y;
	public float w;

	public Vector2f() {
		this.x = 0.0f;
		this.y = 0.0f;
		this.w = 1.0f;
	}

	public Vector2f(Vector2f v) {
		this.x = v.x;
		this.y = v.y;
		this.w = v.w;
	}

	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
		this.w = 1.0f;
	}

	public Vector2f(float x, float y, float w) {
		this.x = x;
		this.y = y;
		this.w = w;
	}

	public void translate(float tx, float ty) {
		x += tx;
		y += ty;
	}

	public void scale(float sx, float sy) {
		x *= sx;
		y *= sy;
	}

	public Vector2f add(Vector2f v) {
		return new Vector2f(x + v.x, y + v.y);
	}

	public Vector2f sub(Vector2f v) {
		return new Vector2f(x - v.x, y - v.y);
	}
}
