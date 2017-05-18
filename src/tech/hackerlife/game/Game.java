package tech.hackerlife.game;

import java.awt.*;
import tech.hackerlife.game.entity.mob.Player;
import tech.hackerlife.game.util.*;
import tech.hackerlife.game.world.*;

public class Game {

	public static int tileSize;
	public static Point mapOrigin, spawn = new Point(0, 0);
	static long time = System.currentTimeMillis(), dTime;
	public static int counter = 0, fps = 0, speed;
	static Level level;
	static Player player;

	public static void init() {
		level = new Level();
		tileSize = (int) (Main.HEIGHT / 9);
		player = new Player(spawn);
		mapOrigin = new Point(spawn);
		speed = (int)(Main.k * 0.037);
	}

	public static void update(Graphics g, int width, int height, int k) {
		Keyboard.update();
		
		player.update(mapOrigin, tileSize, speed, g);

		level.update(g, tileSize, mapOrigin);
		
		player.render(g, tileSize);
		
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