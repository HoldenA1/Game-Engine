package tech.hackerlife.game;

import java.awt.*;
import tech.hackerlife.game.entity.decoration.Tree;
import tech.hackerlife.game.entity.mob.*;
import tech.hackerlife.game.util.*;
import tech.hackerlife.game.world.*;

public class Game {
	public static Point mapOrigin;
	public static int tuts = 10, playerSpeed, tileSize;
	static Level level;
	static Player player;
	static Turtle[] turtle = new Turtle[tuts];
	static Tree palm;
	
	public static void init() {
		Main.f.getContentPane().setCursor(Main.blankCursor); //Erases cursor
		
		tileSize = (int) (Main.HEIGHT / 9); //Initializes the tiles' size
		
		level = new Level();
		
		Point spawn = new Point(tileSize * 10, tileSize * 7); //player's spawn point; numbers are # of tiles out
		player = new Player(spawn);

		for (int i = 0; i < tuts; i++) { //spawns in all the turtles
			turtle[i] = new Turtle();
		}
		
		palm = new Tree(new Point(tileSize*10, tileSize*10));
		
		mapOrigin = new Point(spawn);
		playerSpeed = (int)(Main.scale*5);
		
		System.out.println(Level.entities);
		
		Main.state = GameState.GAME; //starts the game
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

}