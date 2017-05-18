package tech.hackerlife.game.world;

import java.awt.Image;
import tech.hackerlife.game.util.*;

public abstract class Tile {
	Image tile;

	public Tile(String path) {
		tile = Reader.loadBufferedImage(path);
	}
	
	public boolean solid() {
		return false;
	}
	
	public boolean isWet() {
		return false;
	}
	
	public Image getImage() {
		return tile;
	}
	
}