package lh.koneke.games.TerraWatt;

public class Rectangle {
	float x;
	float y;
	float w;
	float h;
	
	public float x() { return this.x; }
	public float y() { return this.y; }
	public float w() { return this.w; }
	public float h() { return this.h; }
	
	public Vector2 tl() {
		return new Vector2(x, y); }
	public Vector2 tr() {
		return new Vector2(x+w, y); }
	public Vector2 bl() {
		return new Vector2(x, y+h); }
	public Vector2 br() {
		return new Vector2(x+w, y+h); }

	public void setx(float x) { this.x = x; }
	public void sety(float y) { this.y = y; }
	public void setw(float w) { this.w = w; }
	public void seth(float h) { this.h = h; }

	public void settl(Vector2 tl) {
		x = tl.x();
		y = tl.y();
	}
	public void settr(Vector2 tr) {
		x = tr.x()-w;
		y = tr.y();
	}
	public void setbl(Vector2 bl) {
		x = bl.x();
		y = bl.y()-h;
	}
	public void setbr(Vector2 br) {
		x = br.x()-w;
		y = br.y()-h;
	}

	public Rectangle clone() { return new Rectangle(x,y,w,h); }

	public Rectangle(float x, float y, float w, float h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
}
