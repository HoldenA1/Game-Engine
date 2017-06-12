package tech.hackerlife.game;

import java.awt.*;
import tech.hackerlife.game.entity.mob.Player;
import tech.hackerlife.game.entity.mob.Turtle;
import tech.hackerlife.game.util.*;
import tech.hackerlife.game.world.*;

public class Game {

	public static int tileSize;
	public static Point mapOrigin;
	static long time = System.currentTimeMillis(), dTime;
	public static int counter = 0, fps = 0, playerSpeed;
	static Level level;
	static Player player;
	static Turtle turtle;

	public static void init() {
		tileSize = (int) (Main.HEIGHT / 9);
		Point spawn = new Point(10 * tileSize, 7 * tileSize);
		level = new Level();
		player = new Player(spawn);
		turtle = new Turtle();
		mapOrigin = new Point(spawn);
		playerSpeed = (int)(Main.HEIGHT / 200);
	}

	public static void update(Graphics g, int width, int height) {
		Keyboard.update();
		
		player.update(mapOrigin);

		level.update(g, tileSize, mapOrigin);
		
		player.render(g);
		
		turtle.update(g);
		
		if (Main.displayFPS) fpsCounter(g);
	}

	public static void fpsCounter(Graphics g) {
		counter++;
		dTime = System.currentTimeMillis();
		if (dTime - time > 1000) {
			fps = counter;
			counter = 0;
			time = System.currentTimeMillis();
		}
		g.setFont(new Font("Dialog", Font.BOLD, Main.HEIGHT / 48));
		g.setColor(Color.yellow);
		g.drawString("FPS: " + fps, 10, Main.HEIGHT / 48);
	}

}