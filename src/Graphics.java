package lh.koneke.games.TerraWatt;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.LWJGLException;

public class Graphics {
	public static int scrw;
	public static int scrh;

	public static void setDisplay(int w, int h) {
		scrw = w;
		scrh = h;
		
		try {
			if(Display.isCreated()) {
				Display.destroy();
			}
			Display.setDisplayMode(new DisplayMode(scrw, scrh));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static void setupOpenGL() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, scrw, scrh, 0, -1, 1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glClearColor(0,0,0,1);
	}

	public static void begin(int mode) {
		GL11.glBegin(mode);
	}

	public static void end() {
		GL11.glEnd();
	}

	public static void point(float x, float y) {
		point(x,y,0,0); }
	public static void point(float x, float y, float tx, float ty) {
		GL11.glTexCoord2f(tx, ty);
		GL11.glVertex2f(x, y);
	}
	
	public static final int Quads = GL11.GL_QUADS;
}
