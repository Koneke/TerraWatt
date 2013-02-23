package lh.koneke.games.TerraWatt;

import org.lwjgl.opengl.GL11; //TODO: see draw function
import org.newdawn.slick.Color;

public class Entity {
	Vector2 position;
	public Vector2 getPosition() { return this.position; }
	public void setPosition(Vector2 v) { this.position = v.clone(); }

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
		draw(position, getSize(), getOffset());
	}
	public void draw(Vector2 position, Vector2 size, Vector2 offset
	/*in the future, pass drawer of some kind instead*/) {
		color.bind();
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(
				position.x()+offset.x(),
				position.y()+offset.y());
			GL11.glVertex2f(
				position.x()+offset.x()+size.x(),
				position.y()+offset.y());
			GL11.glVertex2f(
				position.x()+offset.x()+size.x(),
				position.y()+offset.y()+size.y());
			GL11.glVertex2f(
				position.x()+offset.x(),
				position.y()+offset.y()+size.y());
		GL11.glEnd();
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
