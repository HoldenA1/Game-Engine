package tech.hackerlife.game.entity.mob;

import java.awt.*;
import tech.hackerlife.game.*;
import tech.hackerlife.game.entity.*;
import tech.hackerlife.game.entity.mob.Player.direction;
import tech.hackerlife.game.util.Reader;
import tech.hackerlife.game.world.Level;

public abstract class Mob extends Entity {
	direction dir = direction.DOWN;
	int anim = 0, xConstraint, yConstraint;

	Image up, down, left, right, upWalk, downWalk, leftWalk, rightWalk, upWalk2, downWalk2, leftWalk2, rightWalk2;
	protected Image character;

	public Mob(Point _pos, String a, String b, String c, String d, String e, String f, String g, String h, String i, String j, String k, String l, int _xConstraint, int _yConstraint) {
		super(_pos);
		xConstraint = (int)(Game.tileSize*(_xConstraint/16.0));
		yConstraint = (int)(Game.tileSize*(_yConstraint/16.0));
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
		leftWalk2 = Reader.loadBufferedImage(k);
		rightWalk2 = Reader.loadBufferedImage(l);
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
					character = leftWalk;
				else if (anim <= 30)
					character = leftWalk2;
			}
			if (goRight) {
				dir = direction.RIGHT;
				xa += speed;
				if (anim > 30)
					character = rightWalk;
				else if (anim <= 30)
					character = rightWalk2;
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
		if (!collision(xa, ya)) {
			pos.x += xa;
			pos.y += ya;
		} else {
			if (xa != 0) {
				if (xa>0) xa--;
				else xa++;
				translate(xa, 0);
			} else {
				if (ya>0) ya--;
				else ya++;
				translate(0, ya);
			}
		}
//		if(!water(xa, ya)) {
//			
//		}
	}
	
	private boolean collision(int xa, int ya) {
		if (ya != 0) {
			if (ya < 0) {
				if (Level.getTileAtLocation(pos.x + xConstraint, pos.y + ya + yConstraint).solid()) return true;
				if (Level.getTileAtLocation(pos.x + Game.tileSize-1 - xConstraint, pos.y + ya + yConstraint).solid()) return true;
			} else {
				if (Level.getTileAtLocation(pos.x + xConstraint, pos.y + ya + Game.tileSize-1 - yConstraint).solid()) return true;
				if (Level.getTileAtLocation(pos.x + Game.tileSize-1 - xConstraint, pos.y + ya + Game.tileSize-1 - yConstraint).solid()) return true;
			}
		} else {
			if (xa < 0) {
				if (Level.getTileAtLocation(pos.x + xa + xConstraint, pos.y + yConstraint).solid()) return true;
				if (Level.getTileAtLocation(pos.x + xa + xConstraint, pos.y + Game.tileSize-1 - yConstraint).solid()) return true;
			} else {
				if (Level.getTileAtLocation(pos.x + xa + Game.tileSize-1 - xConstraint, pos.y + yConstraint).solid()) return true;
				if (Level.getTileAtLocation(pos.x + xa + Game.tileSize-1 - xConstraint, pos.y + Game.tileSize-1 - yConstraint).solid()) return true;
			}
		}
		return false;
	}
	
//	private boolean water(int xa, int ya) {
//		if (ya != 0) {
//			if (ya < 0) {
//				if (Level.getTileAtLocation(pos.x, pos.y + ya).isWet()) return true;
//				if (Level.getTileAtLocation(pos.x + Game.tileSize-1, pos.y + ya).isWet()) return true;
//			} else {
//				if (Level.getTileAtLocation(pos.x, pos.y + ya + Game.tileSize-1).isWet()) return true;
//				if (Level.getTileAtLocation(pos.x + Game.tileSize-1, pos.y + ya + Game.tileSize-1).isWet()) return true;
//			}
//		} else {
//			if (xa < 0) {
//				if (Level.getTileAtLocation(pos.x + xa, pos.y).isWet()) return true;
//				if (Level.getTileAtLocation(pos.x + xa, pos.y + Game.tileSize-1).isWet()) return true;
//			} else {
//				if (Level.getTileAtLocation(pos.x + xa + Game.tileSize-1, pos.y).isWet()) return true;
//				if (Level.getTileAtLocation(pos.x + xa + Game.tileSize-1, pos.y + Game.tileSize-1).isWet()) return true;
//			}
//		}
//		return false;
//	}
	
	public void loopMap() {
		if (pos.x < 0) {
			pos.x += (Game.tileSize * Level.map.getWidth());
		} else if (pos.x > (Game.tileSize * Level.map.getWidth())) {
			pos.x -= (Game.tileSize * Level.map.getWidth());
		}
		if (pos.y < 0) {
			pos.y += (Game.tileSize * Level.map.getHeight());
		} else if (pos.y > (Game.tileSize * Level.map.getHeight())) {
			pos.y -= (Game.tileSize * Level.map.getHeight());
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(character, pos.x + Game.mapOrigin.x, pos.y + Game.mapOrigin.y, Game.tileSize, Game.tileSize, null);
	}
	
}