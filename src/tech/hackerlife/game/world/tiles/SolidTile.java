package tech.hackerlife.game.world.tiles;

import tech.hackerlife.game.world.Tile;

public class SolidTile extends Tile {

	public SolidTile(String path) {
		super(path);
	}
	
	public boolean solid() {
		return true;
	}
	
}