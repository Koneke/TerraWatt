package lh.koneke.games.TerraWatt;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.LWJGLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.Color;

import lh.koneke.Guts.AtomController;
import lh.koneke.Guts.Time;

public class Game {
	public static void main(String[] args) {
		Game g = new Game();
		g.run();
	}
	
	public void run() {
		init();
		while(!Display.isCloseRequested()) {
			update();
			draw();
		}
		quit();
	}
	
	public void init() {
		try {
			Display.setDisplayMode(new DisplayMode(800, 640));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 640, 0, -1, 1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glClearColor(0,0,0,1);

		AtomController.setup();
		
		entities = new ArrayList<Entity>();

		Entity player = new Entity();
		player.setPosition(new Vector2(12*32,14*32+16));
		player.setSize(new Vector2(32,32));
		player.setOffset(new Vector2(-16,-16));
		entities.add(player);

		map = new Block[mapWidth][mapHeight];
		for(int x = 0; x < mapWidth; x++) {
			for(int y = 0; y < mapHeight; y++) {
				if(y < 15) {
					map[x][y] = null;
				} else {
					map[x][y] = new Block();
					map[x][y].setColor(new Color(
						random.nextFloat(),
						random.nextFloat(),
						random.nextFloat(),
						1));
				}
			}
		}
	}

	public void quit() { }
	
	List<Entity> entities;
	Block[][] map;

	int mapWidth = 25;
	int mapHeight = 20;

	AtomController controller;

	Random random = new Random();

	public void update() {
		Time.update();
		AtomController.updateAll();
		AtomController.postUpdateAll();
	}
	
	public void draw() {
		for(int x = 0; x < mapWidth; x++) {
			for(int y = 0; y < mapHeight; y++) {
				if(map[x][y] != null) {
					Color c = map[x][y].getColor();
					c.bind();
					GL11.glBegin(GL11.GL_QUADS);
						GL11.glVertex2f( 1+ x*32, 		 1+ y*32);
						GL11.glVertex2f(-1+(x+1)*32,	 1+ y*32);
						GL11.glVertex2f(-1+(x+1)*32,	-1+(y+1)*32);
						GL11.glVertex2f( 1+ x*32,		-1+(y+1)*32);
					GL11.glEnd();
				}
			}
		}
		for(Entity e : entities) {
			e.draw();
		}

		Display.update();
	}
}
