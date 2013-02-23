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

#define scrw 800
#define scrh 640
#define tilesize 32
#define inventoryTileSize 16

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
		Graphics.setDisplay(scrw, scrh);
		Graphics.setupOpenGL();
		AtomController.setup();
		
		entities = new ArrayList<Entity>();
		//inventory = new ArrayList<Item>();
		ninventory = new Inventory();

		Entity player = new Entity();
		player.setPosition(new Vector2(12*tilesize,14*tilesize+16));
		player.setSize(new Vector2(tilesize,tilesize));
		player.setOffset(new Vector2(-tilesize/2,-tilesize/2));
		player.setColor(new Color(1f,0f,0f,1f));
		entities.add(player);

		Item i = new Item();
		i.setPosition(new Vector2(10*tilesize,14*tilesize+16));
		i.setSize(player.getSize().clone());
		i.setOffset(player.getOffset().clone());
		i.setColor(new Color(0f,0f,1f,1f));
		entities.add(i);

		i = new Item();
		i.setPosition(new Vector2(10*tilesize-16,14*tilesize+16));
		i.setSize(player.getSize().clone());
		i.setOffset(player.getOffset().clone());
		i.setColor(new Color(1f,0f,1f,1f));
		entities.add(i);

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

	final int mapWidth = 25;
	final int mapHeight = 20;

	AtomController controller;
	//List<Item> inventory;
	Inventory ninventory;

	Random random = new Random();
	boolean redraw = true;

	Vector2 playerSpeed = new Vector2(0,0);
	Vector2 playerAcc = new Vector2(0,0);

	int dev_fpstimer;
	int dev_fpsupdates;

	public void update() {
		Time.update();
		dev_fpstimer += Time.dt;
		dev_fpsupdates += 1;

		while(dev_fpstimer > 1000) {
			dev_fpstimer-=1000;
			System.out.println(dev_fpsupdates);
			dev_fpsupdates = 0;
		}

		AtomController.updateAll();

		AtomController gamepad = 
			AtomController.getType("Gamepad").get(0);
		float x = gamepad.getValue("x");
		x = (float)Math.floor(x * 10f)/10f;

		if(Math.abs(x) > 0.3) {
			Entity player = entities.get(0);
			Vector2 playerPos = player.getPosition();
			playerPos.setx(playerPos.x()+0.1f*Time.dt*x);
			redraw = true;
		}

		Entity player = entities.get(0);
		if(gamepad.getValue("A") == 1.0f && gamepad.getLast("A") != 1.0f) {
			List<Entity> copy = new ArrayList<Entity>(entities);
			for(Entity e : entities) {
				if(e != player &&
					Vector2.distance(e.getPosition(), player.getPosition()) < tilesize) {
					copy.remove(e);
					Item i = new Item();
					i.setSize(player.getSize().clone());
					i.setOffset(player.getOffset().clone());
					i.setColor(e.getColor());
					ninventory.addItem(i);
					redraw = true;
					break;
				}
			}
			entities = new ArrayList<Entity>(copy);
		}

		if(gamepad.getValue("X") == 1.0f && gamepad.getLast("X") != 1.0f) {
			//jump
		}

		AtomController.postUpdateAll();
	}
	
	public void draw() {
		//TODO: Clean me!
		if(redraw) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			for(int x = 0; x < mapWidth; x++) {
				for(int y = 0; y < mapHeight; y++) {
					if(map[x][y] != null) {
						Color c = map[x][y].getColor();
						c.bind();
						GL11.glBegin(GL11.GL_QUADS);
							GL11.glVertex2f( 1+ x*tilesize, 	 1+ y*tilesize);
							GL11.glVertex2f(-1+(x+1)*tilesize,	 1+ y*tilesize);
							GL11.glVertex2f(-1+(x+1)*tilesize,	-1+(y+1)*tilesize);
							GL11.glVertex2f( 1+ x*tilesize,		-1+(y+1)*tilesize);
						GL11.glEnd();
					}
				}
			}
			for(Entity e : entities) {
				e.draw(e.getPosition());
			}
			//Hud below
			int margin = 2;
			int xpadding = 4;
			int ypadding = 2;

			/*int x1 = 0;
			int x2 = 2*xpadding+inventoryTileSize*inventory.size()+
				(inventory.size()-1)*margin;
			int y1 = 0;
			int y2 = 2*ypadding+inventoryTileSize;*/
			
			/*Color.white.bind();
			Graphics.begin(Graphics.Quads);
				Graphics.vector2f(x1, y1);
				Graphics.vector2f(x2, y1);
				Graphics.vector2f(x2, y2);
				Graphics.vector2f(x1, y2);
			Graphics.end();*/

			ninventory.draw();

			/*int x = xpadding;
			int y = ypadding;
			for(Item i : inventory) {
				i.draw(new Vector2(x, y),
					new Vector2(inventoryTileSize, inventoryTileSize),
					new Vector2(0,0));
				x+=inventoryTileSize+margin;
			}*/

			//redraw done
			redraw = false;
		}
		Display.update();
	}
}
