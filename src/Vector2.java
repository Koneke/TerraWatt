package lh.koneke.games.TerraWatt;

public class Vector2 {
	float x;
	float y;
	
	public Vector2(float x, float y) { this.x = x; this.y = y; }
	public Vector2 clone() { return new Vector2(x, y); }

	public float x() { return x; }
	public float y() { return y; }
}
