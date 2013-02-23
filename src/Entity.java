package lh.koneke.games.TerraWatt;

import org.lwjgl.opengl.GL11; //TODO: see draw function
import org.newdawn.slick.Color;

public class Entity {
	Vector2 position;
	public Vector2 getPosition() { return this.position; }
	public void setPosition(Vector2 v) { this.position = v; }

	Vector2 size;
	public Vector2 getSize() { return this.size; }
	public void setSize(Vector2 v) { this.size = v; }

	Vector2 offset;
	public Vector2 getOffset() { return this.offset; }
	public void setOffset(Vector2 v) { this.offset = v; }

	int depth;
	public int getDepth() { return this.depth; }
	public void setDepth(int i) { this.depth = i; }

	//drawable graphic
	Color color;
	public Color getColor() { return this.color; }
	public void setColor(Color c) { this.color = c; }
	
	public void draw(Vector2 position) {
		draw(position, getSize(), getOffset(), getColor()); }
	public void draw(
		Vector2 position,
		Vector2 size) {
		draw(position, size, getOffset(), getColor()); }
	public void draw(
		Vector2 position,
		Vector2 size,
		Vector2 offset) {
		draw(position, size, offset, getColor()); }
	public void draw(
		Vector2 position,
		Vector2 size,
		Vector2 offset,
		Color color
	/*in the future, pass drawer of some kind instead*/) {
		color.bind();

		float x1 = position.x()+offset.x();
		float x2 = position.x()+offset.x()+size.x();
		float y1 = position.y()+offset.y();
		float y2 = position.y()+offset.y()+size.y();

		Graphics.begin(Graphics.Quads);
			Graphics.point(x1,y1);
			Graphics.point(x2,y1);
			Graphics.point(x2,y2);
			Graphics.point(x1,y2);
		Graphics.end();
	}

	public Entity clone() {
		Entity e = new Entity();
		e.setPosition(getPosition());
		e.setSize(getSize());
		e.setOffset(getOffset());
		e.setDepth(getDepth());
		e.setColor(getColor());
		return e;
	}
}
