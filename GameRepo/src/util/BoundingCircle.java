package util;
/*Bounding circle is a circle region*/
public class BoundingCircle extends Geometry{
	
	Vector2f center;
	float radius;
	public BoundingCircle(float left, float top, float radius) {
		this.center = new Vector2f(left, top);
		this.radius = radius;
	}
	@Override
	public Boolean isInGeo(Vector2f pos) {
		
		float xDis = pos.x - center.x;
		float yDis = pos.y - center.y;
		double distancePower = Math.pow(xDis, 2) + Math.pow(yDis, 2);
		double radiusPower = Math.pow(radius, 2);
		if(distancePower < radiusPower) {
			return true;
		}else {
			return false;
		}
	}

}
