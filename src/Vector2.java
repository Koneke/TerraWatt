package lh.koneke.games.TerraWatt;

public class Vector2 {
	float x;
	float y;
	
	public Vector2(float x, float y) { this.x = x; this.y = y; }
	public Vector2 clone() { return new Vector2(x, y); }

	public float x() { return x; }
	public float y() { return y; }
	public void setx(float x) { this.x = x; }
	public void sety(float y) { this.y = y; }
	
	public Vector2 plus(Vector2 b) {
		return new Vector2(x+b.x(),y+b.y()); }
	public Vector2 times(float scalar) {
		return new Vector2(x*scalar, y*scalar);
	}

	static float distance(Vector2 a, Vector2 b) {
		float dx = Math.abs(a.x()-b.x());
		float dy = Math.abs(a.y()-b.y());
		float d = (float)(Math.pow(dx,2)+Math.pow(dy,2));
		d = (float)Math.sqrt(d);
		return d;
	}
}
