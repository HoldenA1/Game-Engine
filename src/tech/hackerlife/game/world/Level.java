package tech.hackerlife.game.world;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import tech.hackerlife.game.*;
import tech.hackerlife.game.util.Reader;
import tech.hackerlife.game.entity.*;
import tech.hackerlife.game.world.tiles.*;

public class Level {

	static SolidTile bricks = new SolidTile("/textures/tiles/brick.png");
	static DefaultTile grass = new DefaultTile("/textures/tiles/grass.png");

	static public BufferedImage map = Reader.loadBufferedImage("/textures/map.png");
	
	public static ArrayList<Entity> entities = new ArrayList<Entity>();
	
	static int lx, rx, uy, dy;
	static int[] rgb;
	static Tile[][] tiles;
	static int counter= 0;

	public Level() {
		rgb = new int[map.getWidth() * map.getHeight()];
		tiles = new Tile[map.getWidth()][map.getHeight()];
		map.getRGB(0, 0, map.getWidth(), map.getHeight(), rgb, 0, map.getWidth());
		for (int i = 0; i < map.getWidth() * map.getHeight(); i += map.getWidth()) {
			for (int z = 0; z < map.getWidth(); z++) {
				counter++;
				if (Integer.toHexString(rgb[i + z]).equals("ffffffff"))
					tiles[z][i / map.getWidth()] = bricks;
				else if (Integer.toHexString(rgb[i + z]).equals("ff00ff21"))
					tiles[z][i / map.getWidth()] = grass;
			}
		}
	}

	public static Tile getTileAtLocation(int x, int y) {
		if (x < 0) x += map.getWidth() * Game.tileSize;
		else if (x > 0) x -= map.getWidth() * Game.tileSize;
		if (y < 0) y += map.getHeight() * Game.tileSize;
		else if (y > 0) y -= map.getHeight() * Game.tileSize;
		int xa = x / Game.tileSize;
		int ya = y / Game.tileSize;
		return tiles[xa][ya];
	}

	public void update(Graphics g, int tileSize, Point origin) {
		for (int h = 0; h < entities.size(); h++) {
			entities.get(h).render(g);
		}
		for (int i = 0; i < map.getWidth() * tileSize; i += tileSize) {
			int c = i / tileSize;
			for (int z = 0; z < map.getHeight() * tileSize; z += tileSize) {
				int v = z / tileSize;
				lx = i - (tileSize * map.getWidth());
				rx = i + (tileSize * map.getWidth());
				uy = z - (tileSize * map.getHeight());
				dy = z + (tileSize * map.getHeight());
				if (i > -origin.x - tileSize && i < -origin.x + Main.WIDTH && z > -origin.y - tileSize
						&& z < -origin.y + Main.HEIGHT) {
					// draws map
					renderTiles(i, z, tileSize, c, v, g);
				} else if (lx > -origin.x - tileSize && lx < -origin.x + Main.WIDTH && z > -origin.y - tileSize
						&& z < -origin.y + Main.HEIGHT) {
					// draws left map
					renderTiles(lx, z, tileSize, c, v, g);
				} else if (rx > -origin.x - tileSize && rx < -origin.x + Main.WIDTH && z > -origin.y - tileSize
						&& z < -origin.y + Main.HEIGHT) {
					// draws right map
					renderTiles(rx, z, tileSize, c, v, g);
				} else if (i > -origin.x - tileSize && i < -origin.x + Main.WIDTH && uy > -origin.y - tileSize
						&& uy < -origin.y + Main.HEIGHT) {
					// draws top map
					renderTiles(i, uy, tileSize, c, v, g);
				} else if (i > -origin.x - tileSize && i < -origin.x + Main.WIDTH && dy > -origin.y - tileSize
						&& dy < -origin.y + Main.HEIGHT) {
					// draws bottom map
					renderTiles(i, dy, tileSize, c, v, g);
					// draws corner maps
				} else if (lx > -origin.x - tileSize && lx < -origin.x + Main.WIDTH && uy > -origin.y - tileSize
						&& uy < -origin.y + Main.HEIGHT) {
					renderTiles(lx, uy, tileSize, c, v, g);
				} else if (rx > -origin.x - tileSize && rx < -origin.x + Main.WIDTH && dy > -origin.y - tileSize
						&& dy < -origin.y + Main.HEIGHT) {
					renderTiles(rx, dy, tileSize, c, v, g);
				} else if (rx > -origin.x - tileSize && rx < -origin.x + Main.WIDTH && uy > -origin.y - tileSize
						&& uy < -origin.y + Main.HEIGHT) {
					renderTiles(rx, uy, tileSize, c, v, g);
				} else if (lx > -origin.x - tileSize && lx < -origin.x + Main.WIDTH && dy > -origin.y - tileSize
						&& dy < -origin.y + Main.HEIGHT) {
					renderTiles(lx, dy, tileSize, c, v, g);
				}
			}
		}
	}

	public void renderTiles(int x, int y, int tileSize, int c, int v, Graphics g) {
		g.drawImage(tiles[c][v].getImage(), x + Game.mapOrigin.x, y + Game.mapOrigin.y, tileSize, tileSize, null);
	}

}