package tech.hackerlife.game.entity;

import java.awt.*;
import tech.hackerlife.game.world.Level;

public abstract class Entity {
	protected Point pos;
	
	public Entity(Point _pos) {
		pos = _pos;
		add();
	}
	
	public void render(Graphics g) {
	}
	
	public void update() {
	}

	public void add() {
		Level.entities.add(this);
	}

	public void remove() {
		Level.entities.remove(this);
	}
}