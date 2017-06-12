package tech.hackerlife.game.entity.mob;

import java.awt.*;
import java.awt.event.KeyEvent;
import tech.hackerlife.game.*;
import tech.hackerlife.game.util.*;
import tech.hackerlife.game.world.Level;

public class Player extends Mob {
	public static direction dir;
	static int anim = 0;

	static String characterUp = "/textures/character/back.png",
	characterDown = "/textures/character/up.png",
	characterLeft = "/textures/character/left.png",
	characterRight = "/textures/character/right.png",
	characterUpWalk = "/textures/character/walkback.png",
	characterDownWalk = "/textures/character/walkup.png",
	characterLeftWalk = "/textures/character/walkleft.png",
	characterRightWalk = "/textures/character/walkright.png",
	characterUpWalk2 = "/textures/character/walkback2.png",
	characterDownWalk2 = "/textures/character/walkup2.png";

	public Player(Point spawn) {
		super(spawn, characterUp, characterDown, characterLeft, characterRight, characterUpWalk, characterDownWalk, 
				characterLeftWalk, characterRightWalk, characterUpWalk2, characterDownWalk2);
		dir = direction.DOWN;
	}
	
	public enum direction {
		LEFT, RIGHT, UP, DOWN
	}

	public void update(Point mapOrigin) {
		keyInput();
		walk(Game.playerSpeed, Keyboard.left, Keyboard.right, Keyboard.up, Keyboard.down);
		loopMap(mapOrigin);
		Game.playerSpeed = (int)(Main.HEIGHT / 200);;
	}
	
	public void render(Graphics g) {
		g.drawImage(character, (Main.WIDTH / 2) - (Game.tileSize / 2),
				(Main.HEIGHT / 2) - (Game.tileSize / 2), Game.tileSize, Game.tileSize, null);
	}

	public void loopMap(Point origin) {
		if (pos.x < 0) {
			pos.x += (Game.tileSize * Level.map.getWidth());
		}
		if (pos.x > (Game.tileSize * Level.map.getWidth())) {
			pos.x -= (Game.tileSize * Level.map.getWidth());
		}
		if (pos.y < 0) {
			pos.y += (Game.tileSize * Level.map.getHeight());
		}
		if (pos.y > (Game.tileSize * Level.map.getHeight())) {
			pos.y -= (Game.tileSize * Level.map.getHeight());
		}
		
		setOrigin();
	}

	public void keyInput() {
		setOrigin();
		
		if (Keyboard.sprint) {
			Game.playerSpeed *= 1.5;
			anim++;
		}
		
		if (Keyboard.escape) {
			Keyboard.keys[KeyEvent.VK_ESCAPE] = false;
			Main.state = GameState.MAIN_MENU;
		}
		if (Keyboard.interact);
	}
	
	public void setOrigin() {
		Game.mapOrigin.x = -(pos.x - (Main.WIDTH / 2) - (Game.tileSize / 2));
		Game.mapOrigin.y = -(pos.y - (Main.HEIGHT / 2) - (Game.tileSize / 2));
	}

}