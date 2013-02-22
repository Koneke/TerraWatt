package lh.koneke.games.TerraWatt;

public class Entity {
	public Vector2 position;

	Drawable drawable;
	public Drawable getDrawable() {
		return this.drawable;
	}
	public void setDrawable(Drawable d) {
		this.drawable = d;
	}
}
