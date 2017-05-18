package tech.hackerlife.game.world.tiles;

import tech.hackerlife.game.world.Tile;

public class WaterTile extends Tile {

	public WaterTile(String path) {
		super(path);
	}
	
	public boolean isWet() {
		return true;
	}
	
}
