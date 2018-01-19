package tech.hackerlife.game.entity.mob;

import java.awt.*;
import java.awt.event.KeyEvent;
import tech.hackerlife.game.*;
import tech.hackerlife.game.util.*;

public class Player extends Mob {
	public static direction dir;
	static int anim = 0;

	static String characterUp = "/textures/entities/character/back.png",
	characterDown = "/textures/entities/character/up.png",
	characterLeft = "/textures/entities/character/left.png",
	characterRight = "/textures/entities/character/right.png",
	characterUpWalk = "/textures/entities/character/walkback.png",
	characterDownWalk = "/textures/entities/character/walkup.png",
	characterLeftWalk = "/textures/entities/character/walkleft.png",
	characterRightWalk = "/textures/entities/character/walkright.png",
	characterUpWalk2 = "/textures/entities/character/walkback2.png",
	characterDownWalk2 = "/textures/entities/character/walkup2.png";

	public Player(Point spawn) {
		super(spawn, characterUp, characterDown, characterLeft, characterRight, characterUpWalk, characterDownWalk, 
				characterLeft, characterRight, characterUpWalk2, characterDownWalk2, characterLeftWalk, characterRightWalk, (int)(Game.tileSize*0.1875), 0);
		dir = direction.DOWN;
	}
	
	public enum direction {
		LEFT, RIGHT, UP, DOWN
	}

	public void update() {
		keyInput();
		walk(Game.playerSpeed, Keyboard.left, Keyboard.right, Keyboard.up, Keyboard.down);
		loopMap();
		setOrigin();
		Game.playerSpeed = (int)(Main.scale*5);
	}
	
	public void render(Graphics g) {
		g.drawImage(character, (Main.WIDTH >> 1) - (Game.tileSize >> 1),
				(Main.HEIGHT >> 1) - (Game.tileSize >> 1), Game.tileSize, Game.tileSize, null);
	}

	public void keyInput() {
		if (Keyboard.sprint) {
			Game.playerSpeed *= 1.5;
			anim++;
		}
		
		if (Keyboard.escape) {
			Keyboard.keys[KeyEvent.VK_ESCAPE] = false;
			Main.f.getContentPane().setCursor(null);
			Main.state = GameState.MAIN_MENU;
		}
		
		if (Keyboard.interact);
	}
	
	public void setOrigin() {
		Game.mapOrigin.x = -(pos.x - (Main.WIDTH >> 1) + (Game.tileSize >> 1));
		Game.mapOrigin.y = -(pos.y - (Main.HEIGHT >> 1) + (Game.tileSize >> 1));
	}

}