package tech.hackerlife.game.entity.decoration;

import java.awt.Point;
import tech.hackerlife.game.util.Reader;

public class Tree extends Decor {
	
	public Tree(Point pos) {
		super(pos, 1, 2, Reader.loadBufferedImage("/textures/tree/palm.png"));
	}

}