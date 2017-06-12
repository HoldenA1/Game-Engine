package tech.hackerlife.game.entity;

import java.awt.*;
import tech.hackerlife.game.Game;
import tech.hackerlife.game.world.Level;

public abstract class Entity {
	protected Point pos;
	private Image texture;
	
	public Entity(Point _pos) {
		this.pos = _pos;
	}
	
	public Entity(Point _pos, Image _texture) {
		this.pos = _pos;
		this.texture = _texture;
	}
	
	public void render(Graphics g) {
		g.drawImage(texture, pos.x + Game.mapOrigin.x, pos.y + Game.mapOrigin.y, Game.tileSize, Game.tileSize, null);
	}

	public void add() {
		Level.entities.add(this);
	}

	public void remove() {
		Level.entities.remove(this);
	}
}