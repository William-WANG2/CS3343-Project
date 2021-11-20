package util;
/*
 * Bounding box is a box region.
 */
public class BoundingBox extends Geometry{
	
	Vector2f leftUp;
	Vector2f widthHeight;
	
	public BoundingBox(float x, float y, float width, float height) {
		
		widthHeight = new Vector2f(width, height);
		leftUp = new Vector2f(x, y);
	}
	@Override
	public Boolean isInGeo(Vector2f pos) {
		if(pos.x < leftUp.x || pos.x > leftUp.x + widthHeight.x)
			return false;
		if(pos.y < leftUp.y || pos.y > leftUp.y + widthHeight.y)
			return false;
		return true;
	}
	
	public Boolean isInGeo(Vector2d pos) {
		if(pos.x < leftUp.x || pos.x > leftUp.x + widthHeight.x)
			return false;
		if(pos.y < leftUp.y || pos.y > leftUp.y + widthHeight.y)
			return false;
		return true;
	}
}
