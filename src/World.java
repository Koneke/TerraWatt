package lh.koneke.games.TerraWatt;

public class World {
	Block[][] map;
	int worldWidth;
	int worldHeight;
	
	public World(int w, int h) {
		map = new Block[w][h];
		worldWidth = w;
		worldHeight = h;
	}
	
	public int getWidth() { return worldWidth; }
	public int getHeight() { return worldHeight; }

	public void set(int x, int y, Block b) {
		map[x][y] = b;
	}
	public Block get(int x, int y) {
		if(x >= worldWidth || x < 0 || y >= worldHeight || y < 0) { return null; }
		return map[x][y];
	}
}
