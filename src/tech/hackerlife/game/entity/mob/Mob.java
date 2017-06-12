package tech.hackerlife.game.entity.mob;

import java.awt.*;
import tech.hackerlife.game.*;
import tech.hackerlife.game.entity.*;
import tech.hackerlife.game.entity.mob.Player.direction;
import tech.hackerlife.game.util.Reader;

public abstract class Mob extends Entity {
	direction dir = direction.DOWN;
	int anim = 0;

	Image up, down, left, right, upWalk, downWalk, leftWalk, rightWalk, upWalk2, downWalk2;
	protected Image character;

	public Mob(Point _pos, String a, String b, String c, String d, String e, String f, String g, String h, String i, String j) {
		super(_pos);
		up = Reader.loadBufferedImage(a);
		down = Reader.loadBufferedImage(b);
		left = Reader.loadBufferedImage(c);
		right = Reader.loadBufferedImage(d);
		upWalk = Reader.loadBufferedImage(e);
		downWalk = Reader.loadBufferedImage(f);
		leftWalk = Reader.loadBufferedImage(g);
		rightWalk = Reader.loadBufferedImage(h);
		upWalk2 = Reader.loadBufferedImage(i);
		downWalk2 = Reader.loadBufferedImage(j);
		character = down;
	}
	
	public void walk(int speed, boolean goLeft, boolean goRight, boolean goUp, boolean goDown) {
		int xa = 0, ya = 0;
		anim++;
		
		if (anim > 60) anim = 0;
		if (!goLeft && !goRight && !goUp && !goDown)
			anim = 0;
		
		switch(dir) {
		case LEFT:
			character = left;
			break;
		case RIGHT:
			character = right;
			break;
		case UP:
			character = up;
			break;
		case DOWN:
			character = down;
			break;
		}
		
		if (!(goLeft && goRight)) {
			if (goLeft) {
				dir = direction.LEFT;
				xa -= speed;
				if (anim > 30)
					character = left;
				else if (anim <= 30)
					character = leftWalk;
			}
			if (goRight) {
				dir = direction.RIGHT;
				xa += speed;
				if (anim > 30)
					character = right;
				else if (anim <= 30)
					character = rightWalk;
			}
		}
		
		if (!(goUp && goDown)) {
			if (goUp) {
				dir = direction.UP;
				ya -= speed;
				if (anim > 30)
					character = upWalk;
				else if (anim <= 30)
					character = upWalk2;
			}
			if (goDown) {
				dir = direction.DOWN;
				ya += speed;
				if (anim > 30)
					character = downWalk;
				else if (anim <= 30)
					character = downWalk2;
			}
		}
		if (xa != 0 || ya != 0) translate(xa, ya);
	}
	
	public void translate(int xa, int ya) {
		if (xa != 0 && ya != 0) {
			translate(xa, 0);
			translate(0, ya);
			return;
		}
		pos.x += xa;
		pos.y += ya;
//		if (!collision(xa, ya)) {
//			x += xa;
//			y += ya;
//		}
	}
	
	public void render(Graphics g) {
		g.drawImage(character, pos.x + Game.mapOrigin.x, pos.y + Game.mapOrigin.y, Game.tileSize, Game.tileSize, null);
	}
	
}