package tech.hackerlife.game.entity.mob;

import java.awt.*;
import java.awt.event.KeyEvent;
import tech.hackerlife.game.*;
import tech.hackerlife.game.util.*;
import tech.hackerlife.game.world.Level;

public class Player extends Mob {
	public static direction dir;
	static int anim = 0;

	Image characterUp = Reader.loadBufferedImage("/textures/character/back.png");
	Image characterDown = Reader.loadBufferedImage("/textures/character/up.png");
	Image characterLeft = Reader.loadBufferedImage("/textures/character/left.png");
	Image characterRight = Reader.loadBufferedImage("/textures/character/right.png");
	Image characterUpWalk = Reader.loadBufferedImage("/textures/character/walkback.png");
	Image characterDownWalk = Reader.loadBufferedImage("/textures/character/walkup.png");
	Image characterLeftWalk = Reader.loadBufferedImage("/textures/character/walkleft.png");
	Image characterRightWalk = Reader.loadBufferedImage("/textures/character/walkright.png");
	Image characterUpWalk2 = Reader.loadBufferedImage("/textures/character/walkback2.png");
	Image characterDownWalk2 = Reader.loadBufferedImage("/textures/character/walkup2.png");
	Image characterLeftWalk2 = Reader.loadBufferedImage("/textures/character/walkleft2.png");
	public Image character = characterDown;

	public Player(Point spawn) {
		super(spawn);
		dir = direction.DOWN;
	}
	
	public enum direction {
		LEFT, RIGHT, UP, DOWN
	}

	public void update(Point mapOrigin, int tileSize, int speed, Graphics g) {
		keyActions(speed);
		System.out.println(pos.x);
		loopMap(mapOrigin, tileSize);
	}
	
	public void render(Graphics g, int tileSize) {
		g.drawImage(character, (Main.WIDTH / 2) - (tileSize / 2),
				(Main.HEIGHT / 2) - (tileSize / 2), tileSize, tileSize, null);
	}

	public void loopMap(Point origin, int tileSize) {
		if (pos.x < 0) {
			pos.x += (tileSize * Level.map.getWidth());
		}
		if (pos.x > (tileSize * Level.map.getWidth())) {
			pos.x -= (tileSize * Level.map.getWidth());
		}
		if (pos.y < 0) {
			pos.y += (tileSize * Level.map.getHeight());
		}
		if (pos.y > (tileSize * Level.map.getHeight())) {
			pos.y -= (tileSize * Level.map.getHeight());
		}
		Game.mapOrigin.x = -(pos.x - (Main.WIDTH / 2) - (Game.tileSize / 2));
		Game.mapOrigin.y = -(pos.y - (Main.HEIGHT / 2) - (Game.tileSize / 2));
	}

	public void keyActions(int speed) {
		int xa = 0, ya = 0;
		anim++;
		if (Keyboard.sprint) {
			speed *= 1.5;
			anim++;
		}
		if (anim > 60) anim = 0;
		if (!Keyboard.left && !Keyboard.right && !Keyboard.up && !Keyboard.down)
			anim = 0;
		
		switch(dir) {
		case LEFT:
			character = characterLeft;
			break;
		case RIGHT:
			character = characterRight;
			break;
		case UP:
			character = characterUp;
			break;
		case DOWN:
			character = characterDown;
			break;
		}
		
		if (!(Keyboard.left && Keyboard.right)) {
			if (Keyboard.left) {
				dir = direction.LEFT;
				xa -= speed;
				if (anim > 30)
					character = characterLeftWalk;
				else if (anim <= 30)
					character = characterLeftWalk2;
			}
			if (Keyboard.right) {
				dir = direction.RIGHT;
				xa += speed;
				if (anim > 30)
					character = characterRight;
				else if (anim <= 30)
					character = characterRightWalk;
			}
		}
		
		if (!(Keyboard.up && Keyboard.down)) {
			if (Keyboard.up) {
				dir = direction.UP;
				ya -= speed;
				if (anim > 30)
					character = characterUpWalk;
				else if (anim <= 30)
					character = characterUpWalk2;
			}
			if (Keyboard.down) {
				dir = direction.DOWN;
				ya += speed;
				if (anim > 30)
					character = characterDownWalk;
				else if (anim <= 30)
					character = characterDownWalk2;
			}
		}
		if (xa != 0 || ya != 0) translate(xa, ya);
		Game.mapOrigin.x = -(pos.x - (Main.WIDTH / 2) - (Game.tileSize / 2));
		Game.mapOrigin.y = -(pos.y - (Main.HEIGHT / 2) - (Game.tileSize / 2));
		
		if (Keyboard.escape) {
			Keyboard.keys[KeyEvent.VK_ESCAPE] = false;
			Main.state = GameState.MAIN_MENU;
		}
		if (Keyboard.interact);
	}

}