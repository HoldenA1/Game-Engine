package tech.hackerlife.game.entity.mob;

import java.awt.Point;
import tech.hackerlife.game.entity.Entity;

public abstract class Mob extends Entity {

	public Mob(Point _pos) {
		super(_pos);
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
	
}