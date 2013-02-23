package lh.koneke.games.TerraWatt;

import java.util.List;
import java.util.ArrayList;

import org.newdawn.slick.Color;

public class Inventory {
	List<Item> items;
	
	final int gMargin = 2;
	final int gXPadding = 4;
	final int gYPadding = 2;
	final int gTileSize = 16;
	
	int selected = 0;
	
	public Inventory() {
		items = new ArrayList<Item>();
	}

	public void next() {
		if(items.size() == 0) { selected = 0; return; }
		selected = (selected + 1) % items.size(); }
	public void prev() {
		if(items.size() == 0) { selected = 0; return; }
		selected = (items.size() + selected - 1) % items.size(); }

	public Item drop() {
		Item i = items.get(selected);
		items.remove(i);
		if(selected > items.size()-1) {
			selected = items.size()-1;
		}
		return i;
	}

	public boolean empty() {
		return items.size() > 0 ? false : true;
	}
	
	public void addItem(Item i) {
		items.add(i);
		if(items.size() == 1) {
			selected = 0;
		}
	}
	public void removeItem(Item i) {
		items.remove(i);
	}

	public void draw() {
		float x1 = 0;
		float y1 = 0;
		float x2 =
			2*gXPadding+gTileSize*items.size()+
			(items.size()-1)*gMargin;
		float y2 =
			2*gYPadding+gTileSize;

		Color.black.bind();
		Graphics.begin(Graphics.Quads);
			Graphics.point(x1, y1);
			Graphics.point(x2, y1);
			Graphics.point(x2, y2);
			Graphics.point(x1, y2);
		Graphics.end();

		float x = gXPadding;
		float y = gYPadding;
		for(int j = 0; j < items.size(); j++) {
		Item i = items.get(j);
			if(j == selected) {
				i.draw(
					new Vector2(x-1, y-1),
					new Vector2(gTileSize+2, gTileSize+2),
					new Vector2(0,0),
					Color.yellow);
			}
			i.draw(
				new Vector2(x, y),
				new Vector2(gTileSize, gTileSize),
				new Vector2(0,0));
			x += gTileSize+gMargin;
		}
	}
}
