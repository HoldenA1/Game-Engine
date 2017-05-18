package tech.hackerlife.game.entity;

import java.awt.*;
import tech.hackerlife.game.world.Level;

public abstract class Entity {
	protected Point pos;
	
	public Entity(Point _pos) {
		this.pos = _pos;
	}
	
	public void render(Graphics g) {
//		g.drawImage(texture, pos.x, pos.y, tileSize, tileSize, null);
	}

	public void add() {
		Level.entities.add(this);
	}

	public void remove() {
		Level.entities.remove(this);
	}
}