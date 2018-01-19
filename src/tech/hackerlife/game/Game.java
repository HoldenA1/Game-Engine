package tech.hackerlife.game;

import java.awt.*;
import tech.hackerlife.game.entity.mob.*;
import tech.hackerlife.game.util.*;
import tech.hackerlife.game.world.*;

public class Game {
	public static Point mapOrigin;
	static long lastTime = System.currentTimeMillis();
	public static int tuts = 100, playerSpeed, tileSize;
	static Level level;
	static Player player;
	static Turtle[] turtle = new Turtle[tuts];
	
	public static void init() {
		Main.f.getContentPane().setCursor(Main.blankCursor);
		
		tileSize = (int) (Main.HEIGHT / 9);
		
		Point spawn = new Point(tileSize * 10, tileSize * 7);
		level = new Level();
		player = new Player(spawn);
		for (int i = 0; i < tuts; i++) {
			turtle[i] = new Turtle();
		}
		
		mapOrigin = new Point(spawn);
		playerSpeed = (int)(Main.scale*5);
		
		Main.state = GameState.GAME;
	}

	public static void update() {
		Keyboard.update();
		
		for (int k = 0; k < Level.entities.size(); k++) {
			Level.entities.get(k).update();
		}
	}
	
	public static void render(Graphics g) {
		level.render(g, tileSize, mapOrigin);
		
		for (int k = 0; k < Level.entities.size(); k++) {
			Level.entities.get(k).render(g);
		}
	}
	
	public static void loadingScreen(Graphics g) {
		g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		g.setFont(new Font("Dialog", Font.BOLD, Main.HEIGHT / 24));
		g.setColor(Color.WHITE);
		if (System.currentTimeMillis() >= 2000 + lastTime) {
			g.drawString("Loading...", Main.WIDTH / 20, (int)(Main.HEIGHT * 0.9));
			lastTime = System.currentTimeMillis();
		} else if (System.currentTimeMillis() >= 1500 + lastTime) {
			g.drawString("Loading...", Main.WIDTH / 20, (int)(Main.HEIGHT * 0.9));
		} else if (System.currentTimeMillis() >= 1000 + lastTime) {
			g.drawString("Loading..", Main.WIDTH / 20, (int)(Main.HEIGHT * 0.9));
		} else if (System.currentTimeMillis() >= 500 + lastTime) {
			g.drawString("Loading.", Main.WIDTH / 20, (int)(Main.HEIGHT * 0.9));
		} else {
			g.drawString("Loading", Main.WIDTH / 20, (int)(Main.HEIGHT * 0.9));
		}
	}

}