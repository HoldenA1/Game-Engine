package tech.hackerlife.game.entity.decoration;

import java.awt.*;
import tech.hackerlife.game.Game;
import tech.hackerlife.game.entity.Entity;

public class Decor extends Entity {
	Image skin;
	int length, width;
	
	public Decor(Point _pos, int _length, int _width, Image _skin) {
		super(_pos);
		this.length = _length;
		this.width = _width;
		this.skin = _skin;
	}
	
	public void render(Graphics g) {
		g.drawImage(skin, pos.x + Game.mapOrigin.x, pos.y + Game.mapOrigin.y, width, length, null);
	}

}